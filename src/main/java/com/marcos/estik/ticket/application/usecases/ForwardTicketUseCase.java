package com.marcos.estik.ticket.application.usecases;

import java.util.Set;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marcos.estik.security.AuthenticatedUser;
import com.marcos.estik.shared.domain.event.TicketForwardedNotifyEvent;
import com.marcos.estik.shared.domain.user.PermissionEnum;
import com.marcos.estik.ticket.application.command.ForwardCommand;
import com.marcos.estik.ticket.application.ports.in.ForwardTicketPort;
import com.marcos.estik.ticket.application.ports.out.LoadTicketFindFacilityByIdPort;
import com.marcos.estik.ticket.application.ports.out.LoadTicketFindPermissionByPermissionPort;
import com.marcos.estik.ticket.application.ports.out.LoadTicketFindUserByIdPort;
import com.marcos.estik.ticket.application.ports.out.TicketEventPublisherPort;
import com.marcos.estik.ticket.application.ports.out.TicketHistoryRepositoryPort;
import com.marcos.estik.ticket.application.ports.out.TicketRepositoryPort;
import com.marcos.estik.ticket.application.view.TicketUserInfo;
import com.marcos.estik.ticket.application.view.TicketView;
import com.marcos.estik.ticket.domain.Ticket;
import com.marcos.estik.ticket.domain.TicketHistory;
import com.marcos.estik.ticket.domain.enums.StatusEnum;
import com.marcos.estik.ticket.domain.exception.ForwardedRoleInvalidException;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class ForwardTicketUseCase implements ForwardTicketPort {
    
    private final TicketRepositoryPort ticketRepositoryPort;
    private final LoadTicketFindUserByIdPort loadTicketFindUserByIdPort;
    private final TicketEventPublisherPort ticketEventPublisherPort;
    private final TicketHistoryRepositoryPort ticketHistoryRepositoryPort;
    private final LoadTicketFindFacilityByIdPort loadTicketFindFacilityByIdPort;
    private final LoadTicketFindPermissionByPermissionPort loadTicketFindPermissionByPermissionPort;
    
    @Override
    @Transactional 
    public TicketView execute(UUID ticketId, ForwardCommand command, AuthenticatedUser principal) {
        TicketUserInfo forwardUser = loadTicketFindUserByIdPort.execute(command.userId());
        Ticket ticket = ticketRepositoryPort.findTicketById(ticketId);
        Set<UUID> recipientsRole = loadTicketFindPermissionByPermissionPort.execute(PermissionEnum.TICKET_SUPERVISOR);

        if (!recipientsRole.contains(forwardUser.roleId())) {
            throw new ForwardedRoleInvalidException("Role forwarded invalid");
        }

        ticket.forward(principal.id(), forwardUser.id(), command.priority());
        ticketRepositoryPort.save(ticket);
        ticketEventPublisherPort.execute(
            new TicketForwardedNotifyEvent(
                ticketId, 
                ticket.getTitle(), 
                ticket.getRequesterId(),
                ticket.getForwardedId(),
                principal.id()
            )
        );

        TicketHistory ticketHistory = TicketHistory.create(ticketId, StatusEnum.FORWARDED, ticket.getTicketType());
        ticketHistoryRepositoryPort.save(ticketHistory);

        return new TicketView(
            ticket,
            loadTicketFindUserByIdPort.execute(ticket.getRequesterId()),
            ticket.getRegulatorId() == null ? null : loadTicketFindUserByIdPort.execute(ticket.getRegulatorId()),
            forwardUser,
            ticket.getAssigneeId() == null ? null : loadTicketFindUserByIdPort.execute(ticket.getAssigneeId()),
            loadTicketFindFacilityByIdPort.execute(ticket.getFacilityId())
        );
    }
    
}
