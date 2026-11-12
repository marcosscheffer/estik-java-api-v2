package com.marcos.estik.ticket.application.usecases;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marcos.estik.security.AuthenticatedUser;
import com.marcos.estik.shared.domain.event.TicketCreateNotifyEvent;
import com.marcos.estik.ticket.application.command.CreateTicketCommand;
import com.marcos.estik.ticket.application.ports.in.CreateTicketPort;
import com.marcos.estik.ticket.application.ports.out.TicketRepositoryPort;
import com.marcos.estik.ticket.application.view.TicketFacilityInfo;
import com.marcos.estik.ticket.application.view.TicketView;
import com.marcos.estik.ticket.application.ports.out.LoadTicketFindFacilityByIdPort;
import com.marcos.estik.ticket.application.ports.out.LoadTicketFindSpecializationByIdPort;
import com.marcos.estik.ticket.application.ports.out.LoadTicketFindUserByIdPort;
import com.marcos.estik.ticket.application.ports.out.TicketEventPublisherPort;
import com.marcos.estik.ticket.application.ports.out.TicketHistoryRepositoryPort;
import com.marcos.estik.ticket.domain.Ticket;
import com.marcos.estik.ticket.domain.TicketHistory;
import com.marcos.estik.ticket.domain.enums.StatusEnum;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class CreateTicketUseCase implements CreateTicketPort {
    
    private final TicketRepositoryPort ticketRepositoryPort;
    private final TicketHistoryRepositoryPort ticketHistoryRepositoryPort;
    private final LoadTicketFindFacilityByIdPort loadTicketFindFacilityByIdPort;
    private final LoadTicketFindUserByIdPort loadTicketFindUserByIdPort;
    private final TicketEventPublisherPort ticketEventPublisherPort;
    private final LoadTicketFindSpecializationByIdPort loadTicketFindSpecializationByIdPort;

    @Override
    @Transactional 
    public TicketView execute(CreateTicketCommand command, AuthenticatedUser principal) {
        TicketFacilityInfo facilityInfo = loadTicketFindFacilityByIdPort.execute(command.facilityId());
        Ticket ticket = Ticket.create(
            command.title(),
            command.description(),
            command.specializationId(),
            principal.id(), 
            command.facilityId(),
            command.priority()
        );

        TicketHistory ticketHistory = TicketHistory.create(
            ticket.getId(), 
            StatusEnum.OPEN, 
            ticket.getSpecializationId()
        );

        ticketRepositoryPort.save(ticket);
        ticketHistoryRepositoryPort.save(ticketHistory);

        ticketEventPublisherPort.execute(
            new TicketCreateNotifyEvent(
                ticket.getId(),
                ticket.getTitle(),
                ticket.getSpecializationId(),
                principal.id()
            )
        );

        return new TicketView(
            ticket,
            loadTicketFindUserByIdPort.execute(ticket.getRequesterId()),
            null,
            null,
            null,
            facilityInfo,
            loadTicketFindSpecializationByIdPort.execute(ticket.getSpecializationId())
        );
    }
}
