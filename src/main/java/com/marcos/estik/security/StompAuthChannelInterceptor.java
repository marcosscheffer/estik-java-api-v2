package com.marcos.estik.security;

import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jspecify.annotations.Nullable;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.marcos.estik.auth.application.port.out.TokenProviderPort;
import com.marcos.estik.ticket.application.ports.in.CheckTicketMessagePort;

import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class StompAuthChannelInterceptor implements ChannelInterceptor {

    private static final Pattern TICKET_DESTINATION = Pattern.compile(
        "^/topic/tickets/([0-9a-fA-f-]{36})/messages$"
    );

    private final TokenProviderPort tokenProviderPort;
    private final CheckTicketMessagePort checkTicketMessagePort;


    @Override
    public @Nullable Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);

        if (accessor == null || accessor.getCommand() == null) {
            return message;
        }


        switch (accessor.getCommand()) {
            case CONNECT:
                accessor.setUser(authenticate(accessor));
                break;
            case SUBSCRIBE:
                authorize(accessor);
                break;
            case SEND:
                throw new AccessDeniedException("Sending through WebSocket is not allowed");
            default:
                break;
        }

        return message;
    }


    private Authentication authenticate(StompHeaderAccessor accesor) {
        String authorization = accesor.getFirstNativeHeader("authorization");

        if (authorization == null || !authorization.startsWith("bearer ")) {
            throw new AccessDeniedException("missing token");
        }

        String token = authorization.substring(7);

        if(!tokenProviderPort.isValidAccessToken(token)) {
            throw new AccessDeniedException("token invalid");
        }

        AuthenticatedUser principal = new AuthenticatedUser(
            tokenProviderPort.getUserId(token),
            tokenProviderPort.getUsername(token),
            tokenProviderPort.getRole(token)
        );

        return new UsernamePasswordAuthenticationToken(
            principal, 
            null,
            List.of(new SimpleGrantedAuthority("ROLE_" + principal.role()))
         );
    }

    public void authorize(StompHeaderAccessor accessor) {
        if (!(accessor.getUser() instanceof Authentication authentication)) {
            throw new AccessDeniedException("not authenticated");
        }

        String destination = accessor.getDestination();

        Matcher matcher = destination == null ? null : TICKET_DESTINATION.matcher(destination);

        if (matcher == null || !matcher.matches()) {
            throw new AccessDeniedException("Destination not allowed");
        }

        UUID ticketId = UUID.fromString(matcher.group(1));
        AuthenticatedUser principal = (AuthenticatedUser) authentication.getPrincipal();

        checkTicketMessagePort.execute(ticketId, principal);

    }

    
}
