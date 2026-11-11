package com.marcos.estik.ticket.application.usecases;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marcos.estik.security.AuthenticatedUser;
import com.marcos.estik.shared.domain.event.TicketResolveNotifyEvent;
import com.marcos.estik.ticket.application.ports.in.ResolveTicketPort;
import com.marcos.estik.ticket.application.ports.out.LoadTicketFindFacilityByIdPort;
import com.marcos.estik.ticket.application.ports.out.LoadTicketFindSpecializationByIdPort;
import com.marcos.estik.ticket.application.ports.out.LoadTicketFindUserByIdPort;
import com.marcos.estik.ticket.application.ports.out.TicketEventPublisherPort;
import com.marcos.estik.ticket.application.ports.out.TicketHistoryRepositoryPort;
import com.marcos.estik.ticket.application.ports.out.TicketRepositoryPort;
import com.marcos.estik.ticket.application.view.TicketView;
import com.marcos.estik.ticket.domain.Ticket;
import com.marcos.estik.ticket.domain.TicketHistory;
import com.marcos.estik.ticket.domain.enums.StatusEnum;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class ResolveTicketUseCase implements ResolveTicketPort {

    private final TicketRepositoryPort ticketRepositoryPort;
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
            loadTicketFindUserByIdPort.execute(ticket.getForwardedId()),
            loadTicketFindUserByIdPort.execute(ticket.getAssigneeId()),
            loadTicketFindFacilityByIdPort.execute(ticket.getFacilityId()),
            loadTicketFindSpecializationByIdPort.execute(ticket.getSpecializationId())
        );
    }
}
