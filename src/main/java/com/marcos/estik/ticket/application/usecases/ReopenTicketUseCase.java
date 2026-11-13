package com.marcos.estik.ticket.application.usecases;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marcos.estik.security.AuthenticatedUser;
import com.marcos.estik.shared.domain.event.TicketReopenNotifyEvent;
import com.marcos.estik.ticket.application.ports.in.ReopenTicketPort;
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

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class ReopenTicketUseCase implements ReopenTicketPort {

    private final TicketRepositoryPort ticketRepositoryPort;
    private final TicketHistoryRepositoryPort ticketHistoryRepositoryPort;
    private final ForwardedRepositoryPort forwardedRepositoryPort;
    private final AssigneedRepositoryPort assigneedRepositoryPort;
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
            StatusEnum.PROGRESS, 
            ticket.getSpecializationId()
        );

        ticket.reopen(principal.id());
        ticketRepositoryPort.save(ticket);
        ticketHistoryRepositoryPort.save(ticketHistory);

        ticketEventPublisherPort.execute(
            new TicketReopenNotifyEvent(
                ticket.getId(),
                ticket.getTitle(),
                assigneedRepositoryPort.searchAssigneed(ticket.getId())
                .stream()
                .map(assigneeUser -> assigneeUser.getUserId())
                .toList(),
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
            assigneedRepositoryPort.searchAssigneed(ticket.getId())
            .stream()
            .map(
                assigneeUser -> new TicketAssigneedInfo(
                    assigneeUser.getId(),
                    loadTicketFindUserByIdPort.execute(assigneeUser.getUserId())
                )
            ).toList()
        );
    }
}
