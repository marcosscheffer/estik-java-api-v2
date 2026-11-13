package com.marcos.estik.ticket.application.usecases;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marcos.estik.security.AuthenticatedUser;
import com.marcos.estik.shared.domain.user.PermissionEnum;
import com.marcos.estik.ticket.application.ports.in.FindTicketByIdPort;
import com.marcos.estik.ticket.application.ports.out.AssigneedRepositoryPort;
import com.marcos.estik.ticket.application.ports.out.ForwardedRepositoryPort;
import com.marcos.estik.ticket.application.ports.out.LoadTicketFindFacilityByIdPort;
import com.marcos.estik.ticket.application.ports.out.LoadTicketFindSpecializationByIdPort;
import com.marcos.estik.ticket.application.ports.out.LoadTicketFindUserByIdPort;
import com.marcos.estik.ticket.application.ports.out.TicketRepositoryPort;
import com.marcos.estik.ticket.application.view.TicketAssigneedInfo;
import com.marcos.estik.ticket.application.view.TicketForwardedInfo;
import com.marcos.estik.ticket.application.view.TicketView;
import com.marcos.estik.ticket.domain.Ticket;
import com.marcos.estik.ticket.domain.exception.TicketOwnerShipException;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class FindTicketByIdUseCase implements FindTicketByIdPort {

    private final TicketRepositoryPort ticketRepositoryPort;
    private final AssigneedRepositoryPort assigneedRepositoryPort;
    private final ForwardedRepositoryPort forwardedRepositoryPort;
    private final LoadTicketFindFacilityByIdPort loadTicketFindFacilityByIdPort;
    private final LoadTicketFindUserByIdPort loadTicketFindUserByIdPort;
    private final LoadTicketFindSpecializationByIdPort loadTicketFindSpecializationByIdPort;

    @Override
    @Transactional(readOnly = true)
    public TicketView execute(UUID id, AuthenticatedUser principal) {
        Ticket ticket = ticketRepositoryPort.findTicketById(id);
        List<TicketAssigneedInfo> assignees = assigneedRepositoryPort.searchAssigneed(ticket.getId())
            .stream()
            .map(
                assigneeUser -> new TicketAssigneedInfo(
                    assigneeUser.getId(),
                    loadTicketFindUserByIdPort.execute(assigneeUser.getUserId())
                )
            ).toList();
        
        List<TicketForwardedInfo> forwarded = forwardedRepositoryPort.searchForwarded(ticket.getId())
            .stream()
            .map(
                forwardedUser -> new TicketForwardedInfo(
                    forwardedUser.getId(),
                    loadTicketFindUserByIdPort.execute(forwardedUser.getUserId())
                )
            ).toList();

        if(!principal.id().equals(ticket.getRequesterId())
            && !assignees.stream().anyMatch(assigneeUser -> assigneeUser.user().id().equals(principal.id()))
            && !forwarded.stream().anyMatch(forwardedUser -> forwardedUser.user().id().equals(principal.id()))
            && !principal.has(PermissionEnum.TICKET_SUPERVISOR)
        ) {
            throw new TicketOwnerShipException("You cannot view this ticket");
        }
        
        return new TicketView(
            ticket,
            loadTicketFindUserByIdPort.execute(ticket.getRequesterId()),
            ticket.getRegulatorId() == null ? null : loadTicketFindUserByIdPort.execute(ticket.getRegulatorId()),
            loadTicketFindFacilityByIdPort.execute(ticket.getFacilityId()),
            ticket.getSpecializationId() == null ? null : loadTicketFindSpecializationByIdPort.execute(ticket.getSpecializationId()),
            forwarded,
            assignees
        );
    }
    
}
