package com.marcos.estik.ticket.application.usecases;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marcos.estik.security.AuthenticatedUser;
import com.marcos.estik.shared.domain.event.TicketCancelledNotifyEvent;
import com.marcos.estik.ticket.application.ports.in.CancelTicketPort;
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
public class CancelTicketUseCase implements CancelTicketPort {

    private final TicketRepositoryPort ticketRepositoryPort;
    private final TicketHistoryRepositoryPort ticketHistoryRepositoryPort;
    private final LoadTicketFindFacilityByIdPort loadTicketFindFacilityByIdPort;
    private final LoadTicketFindUserByIdPort loadTicketFindUserByIdPort;
    private final TicketEventPublisherPort ticketEventPublisherPort;
    private final LoadTicketFindSpecializationByIdPort loadTicketFindSpecializationByIdPort;

    @Override
    @Transactional 
    public TicketView execute(UUID ticketId, AuthenticatedUser principal) {
        Ticket ticket = ticketRepositoryPort.findTicketById(ticketId);
        TicketHistory ticketHistory = TicketHistory.create(
            ticket.getId(), 
            StatusEnum.CANCELLED, 
            ticket.getSpecializationId()
        );
        ticket.cancel(principal.id());
        ticketRepositoryPort.save(ticket);
        ticketHistoryRepositoryPort.save(ticketHistory);

        ticketEventPublisherPort.execute(
            new TicketCancelledNotifyEvent(
                ticket.getId(),
                ticket.getTitle(),
                ticket.getAssigneeId(),
                principal.id()
            )
        );

        return new TicketView(
            ticket,
            loadTicketFindUserByIdPort.execute(ticket.getRequesterId()),
            ticket.getRegulatorId() == null ? null : loadTicketFindUserByIdPort.execute(ticket.getRegulatorId()),
            ticket.getForwardedId() == null ? null : loadTicketFindUserByIdPort.execute(ticket.getForwardedId()),
            ticket.getAssigneeId() == null ? null : loadTicketFindUserByIdPort.execute(ticket.getAssigneeId()),
            loadTicketFindFacilityByIdPort.execute(ticket.getFacilityId()),
            loadTicketFindSpecializationByIdPort.execute(ticket.getSpecializationId())
        );
    }
    
}
