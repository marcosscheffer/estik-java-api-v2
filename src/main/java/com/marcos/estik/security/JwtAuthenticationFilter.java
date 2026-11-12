package com.marcos.estik.security;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.marcos.estik.auth.application.port.out.TokenProviderPort;
import com.marcos.estik.shared.domain.user.PermissionEnum;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final TokenProviderPort tokenProvider;

    @Override
    protected void doFilterInternal(
        HttpServletRequest request, 
        HttpServletResponse response, 
        FilterChain filterChain
    )
        throws ServletException, IOException {
        
            String token = extractToken(request);

            if(
                token != null && 
                tokenProvider.isValidAccessToken(token) &&
                SecurityContextHolder.getContext().getAuthentication() == null
            ) {
                UUID userId = tokenProvider.getUserId(token);
                String username = tokenProvider.getUsername(token);
                UUID specializationId = tokenProvider.getSpecializationId(token);
                Set<PermissionEnum> permissions = tokenProvider.getPermission(token);                
                AuthenticatedUser principal = new AuthenticatedUser(
                    userId,
                    username,
                    specializationId,
                    permissions
                );

                List<GrantedAuthority> authorities = permissions.stream()
                    .map(permission -> (GrantedAuthority) new SimpleGrantedAuthority(permission.name()))
                    .toList();

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    principal,
                    null,
                    authorities
                );

                SecurityContextHolder
                    .getContext()
                    .setAuthentication(authentication);

                }
            filterChain.doFilter(request, response);
    }

    private String extractToken(HttpServletRequest request) {
        String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            return null;
        }

        return authorization.substring(7);
    }
    
}
