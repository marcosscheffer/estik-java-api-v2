package com.marcos.estik.ticket.application.usecases;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marcos.estik.security.AuthenticatedUser;
import com.marcos.estik.shared.domain.event.TicketForwardedNotifyEvent;
import com.marcos.estik.shared.domain.user.PermissionEnum;
import com.marcos.estik.ticket.application.command.ForwardCommand;
import com.marcos.estik.ticket.application.ports.in.ForwardTicketPort;
import com.marcos.estik.ticket.application.ports.out.ForwardedRepositoryPort;
import com.marcos.estik.ticket.application.ports.out.LoadTicketFindFacilityByIdPort;
import com.marcos.estik.ticket.application.ports.out.LoadTicketFindPermissionByPermissionPort;
import com.marcos.estik.ticket.application.ports.out.LoadTicketFindSpecializationByIdPort;
import com.marcos.estik.ticket.application.ports.out.LoadTicketFindUserByIdPort;
import com.marcos.estik.ticket.application.ports.out.TicketEventPublisherPort;
import com.marcos.estik.ticket.application.ports.out.TicketHistoryRepositoryPort;
import com.marcos.estik.ticket.application.ports.out.TicketRepositoryPort;
import com.marcos.estik.ticket.application.view.TicketForwardedInfo;
import com.marcos.estik.ticket.application.view.TicketUserInfo;
import com.marcos.estik.ticket.application.view.TicketView;
import com.marcos.estik.ticket.domain.Forwarded;
import com.marcos.estik.ticket.domain.Ticket;
import com.marcos.estik.ticket.domain.TicketHistory;
import com.marcos.estik.ticket.domain.enums.StatusEnum;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class ForwardTicketUseCase implements ForwardTicketPort {
    
    private final TicketRepositoryPort ticketRepositoryPort;
    private final LoadTicketFindUserByIdPort loadTicketFindUserByIdPort;
    private final TicketEventPublisherPort ticketEventPublisherPort;
    private final ForwardedRepositoryPort forwardedRepositoryPort;
    private final TicketHistoryRepositoryPort ticketHistoryRepositoryPort;
    private final LoadTicketFindFacilityByIdPort loadTicketFindFacilityByIdPort;
    private final LoadTicketFindPermissionByPermissionPort loadTicketFindPermissionByPermissionPort;
    private final LoadTicketFindSpecializationByIdPort loadTicketFindSpecializationByIdPort;
    
    @Override
    @Transactional 
    public TicketView execute(UUID ticketId, ForwardCommand command, AuthenticatedUser principal) {
        Ticket ticket = ticketRepositoryPort.findTicketById(ticketId);

        Set<UUID> recipientsRole = loadTicketFindPermissionByPermissionPort.execute(
            List.copyOf(
                PermissionEnum.grantedBy(PermissionEnum.TICKET_WORK)
            )
        );

        List<TicketUserInfo> users = command.usersId()
            .stream()
            .map(userId -> loadTicketFindUserByIdPort.execute(userId))
            .filter(
                userInfo -> (
                    recipientsRole.contains(userInfo.roleId()) 
                    && !forwardedRepositoryPort.existsByTicketIdAndUserId(ticketId, userInfo.id())
                )
            )
            .toList();
        
        List<Forwarded> usersForwarded = users
            .stream()
            .map(user -> Forwarded.create(user.id(), ticketId))
            .toList();

        usersForwarded.forEach(forwardedRepositoryPort::save);

        ticket.forward();
        ticketRepositoryPort.save(ticket);
        ticketEventPublisherPort.execute(
            new TicketForwardedNotifyEvent(
                ticketId, 
                ticket.getTitle(), 
                ticket.getRequesterId(),
                usersForwarded.stream().map(forwarded -> forwarded.getId()).toList(),
                principal.id()
            )
        );

        TicketHistory ticketHistory = TicketHistory.create(ticketId, StatusEnum.FORWARDED, ticket.getSpecializationId());
        ticketHistoryRepositoryPort.save(ticketHistory);

        return new TicketView(
            ticket,
            loadTicketFindUserByIdPort.execute(ticket.getRequesterId()),
            ticket.getRegulatorId() == null ? null : loadTicketFindUserByIdPort.execute(ticket.getRegulatorId()),
            loadTicketFindFacilityByIdPort.execute(ticket.getFacilityId()),
            ticket.getSpecializationId() == null ? null : loadTicketFindSpecializationByIdPort.execute(ticket.getSpecializationId()),
            usersForwarded.stream()
            .map(
                userForwarded -> new TicketForwardedInfo(
                    userForwarded.getId(),
                    loadTicketFindUserByIdPort.execute(userForwarded.getUserId())
                )
            ).toList(),
            List.of()
        );
    }
    
}
