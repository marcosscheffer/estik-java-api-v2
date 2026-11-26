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
import com.marcos.estik.ticket.application.ports.out.AssigneedRepositoryPort;
import com.marcos.estik.ticket.application.ports.out.ForwardedRepositoryPort;
import com.marcos.estik.ticket.application.ports.out.LoadTicketDeactiveParticipantByUserIdAndChatIdPort;
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
import com.marcos.estik.ticket.domain.Assigneed;
import com.marcos.estik.ticket.domain.Forwarded;
import com.marcos.estik.ticket.domain.Ticket;
import com.marcos.estik.ticket.domain.TicketHistory;
import com.marcos.estik.ticket.domain.enums.StatusEnum;
import com.marcos.estik.ticket.domain.exception.ForwardedRoleInvalidException;
import com.marcos.estik.ticket.domain.exception.TicketOwnerShipException;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class ForwardTicketUseCase implements ForwardTicketPort {
    
    private final TicketRepositoryPort ticketRepositoryPort;
    private final LoadTicketFindUserByIdPort loadTicketFindUserByIdPort;
    private final TicketEventPublisherPort ticketEventPublisherPort;
    private final ForwardedRepositoryPort forwardedRepositoryPort;
    private final LoadTicketDeactiveParticipantByUserIdAndChatIdPort loadTicketDeactiveParticipantByUserIdAndChatIdPort;
    private final AssigneedRepositoryPort assigneedRepositoryPort;
    private final TicketHistoryRepositoryPort ticketHistoryRepositoryPort;
    private final LoadTicketFindFacilityByIdPort loadTicketFindFacilityByIdPort;
    private final LoadTicketFindPermissionByPermissionPort loadTicketFindPermissionByPermissionPort;
    private final LoadTicketFindSpecializationByIdPort loadTicketFindSpecializationByIdPort;
    
    @Override
    @Transactional 
    public TicketView execute(UUID ticketId, ForwardCommand command, AuthenticatedUser principal) {
        Ticket ticket = ticketRepositoryPort.findTicketById(ticketId);

        if(!principal.has(PermissionEnum.TICKET_MANAGE)
            && !(principal.has(PermissionEnum.TICKET_SUPERVISOR)
                && principal.specializationId() != null
                && principal.specializationId().equals(ticket.getSpecializationId())
            )
        ) {
            throw new TicketOwnerShipException("You cannot view this ticket");
        }

        TicketHistory ticketHistory;

        List<Forwarded> oldForward = forwardedRepositoryPort.searchForwarded(ticket.getId());
        List<Assigneed> oldAssigneeds = assigneedRepositoryPort.searchAssigneed(ticket.getId());

        if (!oldForward.isEmpty()) {
            forwardedRepositoryPort.deleteAllByTicketId(ticket.getId());
        }

        if (!oldAssigneeds.isEmpty()) {
            oldAssigneeds.forEach(
                assignee -> {
                    assigneedRepositoryPort.delete(assignee.getId());
                    loadTicketDeactiveParticipantByUserIdAndChatIdPort.execute(assignee.getUserId(), ticket.getChatId());
                }
            );
            ticket.reforward(command.priority(), principal.id());
            ticketHistory = TicketHistory.create(ticket.getId(), StatusEnum.REFORWARDED, ticket.getSpecializationId());

        } else {
            ticket.forward(command.priority(), principal.id());
            ticketHistory = TicketHistory.create(ticketId, StatusEnum.FORWARDED, ticket.getSpecializationId());
        }
        ticketHistoryRepositoryPort.save(ticketHistory);
        ticketRepositoryPort.save(ticket);

        if (command.usersId().stream().anyMatch(id -> id.equals(ticket.getRequesterId()))) {
            throw new TicketOwnerShipException("ticket not be forwarded for requester");
        }

        List<Forwarded> usersForwarded = getUsersForwarded(command.usersId(), ticket.getId());
        usersForwarded.forEach(forwardedRepositoryPort::save);

        ticketEventPublisherPort.execute(
            new TicketForwardedNotifyEvent(
                ticketId, 
                ticket.getTitle(), 
                ticket.getRequesterId(),
                usersForwarded.stream().map(forwarded -> forwarded.getUserId()).toList(),
                principal.id()
            )
        );

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


    private List<Forwarded> getUsersForwarded(List<UUID> usersId, UUID ticketId) {
        Set<UUID> recipientsRole = loadTicketFindPermissionByPermissionPort.execute(
            List.copyOf(
                PermissionEnum.grantedBy(PermissionEnum.TICKET_WORK)
            )
        );

        List<TicketUserInfo> users = usersId
            .stream()
            .distinct()
            .map(userId -> loadTicketFindUserByIdPort.execute(userId))
            .toList();

        if (users.stream().anyMatch(userInfo -> !recipientsRole.contains(userInfo.roleId()))) {
            throw new ForwardedRoleInvalidException("User permission invalid");
        }
        
        return users
            .stream()
            .map(user -> Forwarded.create(user.id(), ticketId))
            .toList();
    }
    
}
