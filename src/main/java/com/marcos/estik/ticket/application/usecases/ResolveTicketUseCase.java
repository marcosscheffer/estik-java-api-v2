package com.marcos.estik.ticket.application.usecases;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marcos.estik.security.AuthenticatedUser;
import com.marcos.estik.shared.domain.event.TicketResolveNotifyEvent;
import com.marcos.estik.ticket.application.ports.in.ResolveTicketPort;
import com.marcos.estik.ticket.application.ports.out.AssigneedRepositoryPort;
import com.marcos.estik.ticket.application.ports.out.ForwardedRepositoryPort;
import com.marcos.estik.ticket.application.ports.out.LoadTicketFindFacilityByIdPort;
import com.marcos.estik.ticket.application.ports.out.LoadTicketFindSpecializationByIdPort;
import com.marcos.estik.ticket.application.ports.out.LoadTicketFindUserByIdPort;
import com.marcos.estik.ticket.application.ports.out.TicketEventPublisherPort;
import com.marcos.estik.ticket.application.ports.out.TicketHistoryRepositoryPort;
import com.marcos.estik.ticket.application.ports.out.TicketRepositoryPort;
import com.marcos.estik.ticket.application.view.TicketAssigneedInfo;
import com.marcos.estik.ticket.application.view.TicketForwardedInfo;
import com.marcos.estik.ticket.application.view.TicketView;
import com.marcos.estik.ticket.domain.Ticket;
import com.marcos.estik.ticket.domain.TicketHistory;
import com.marcos.estik.ticket.domain.enums.StatusEnum;
import com.marcos.estik.ticket.domain.exception.TicketOwnerShipException;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class ResolveTicketUseCase implements ResolveTicketPort {

    private final TicketRepositoryPort ticketRepositoryPort;
    private final ForwardedRepositoryPort forwardedRepositoryPort;
    private final AssigneedRepositoryPort assigneedRepositoryPort;
    private final TicketHistoryRepositoryPort ticketHistoryRepositoryPort;
    private final LoadTicketFindFacilityByIdPort loadTicketFindFacilityByIdPort;
    private final LoadTicketFindUserByIdPort loadTicketFindUserByIdPort;
    private final TicketEventPublisherPort ticketEventPublisherPort;
    private final LoadTicketFindSpecializationByIdPort loadTicketFindSpecializationByIdPort;

    @Override
    @Transactional 
    public TicketView execute(UUID id, AuthenticatedUser principal) {
        Ticket ticket = ticketRepositoryPort.findTicketById(id);

        TicketHistory ticketHistory = TicketHistory.create(
            ticket.getId(), 
            StatusEnum.RESOLVED, 
            ticket.getSpecializationId()
        );

        List<TicketAssigneedInfo> assignees = assigneedRepositoryPort.searchAssigneed(ticket.getId())
            .stream()
            .map(
                assigneeUser -> new TicketAssigneedInfo(
                    assigneeUser.getId(),
                    loadTicketFindUserByIdPort.execute(assigneeUser.getUserId())
                )
            ).toList();

        if (!assignees.stream().anyMatch(assigneeUser -> assigneeUser.user().id().equals(principal.id()))) {
            throw new TicketOwnerShipException("Ticket not be resolved for you");
        }

        ticket.resolve(principal.id());
        ticketRepositoryPort.save(ticket);
        ticketHistoryRepositoryPort.save(ticketHistory);

        ticketEventPublisherPort.execute(
            new TicketResolveNotifyEvent(
                ticket.getId(),
                ticket.getTitle(),
                ticket.getRequesterId(),
                principal.id()
            )
        );

        return new TicketView(
            ticket,
            loadTicketFindUserByIdPort.execute(ticket.getRequesterId()),
            loadTicketFindUserByIdPort.execute(ticket.getRegulatorId()),
            loadTicketFindFacilityByIdPort.execute(ticket.getFacilityId()),
            ticket.getSpecializationId() == null ? null : loadTicketFindSpecializationByIdPort.execute(ticket.getSpecializationId()),
            forwardedRepositoryPort.searchForwarded(ticket.getId()).stream()
            .map(
                userForwarded -> new TicketForwardedInfo(
                    userForwarded.getId(),
                    loadTicketFindUserByIdPort.execute(userForwarded.getUserId())
                )
            ).toList(),
            assignees
        );
    }
}
