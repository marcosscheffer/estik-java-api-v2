package com.marcos.estik.ticket.application.usecases;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marcos.estik.security.AuthenticatedUser;
import com.marcos.estik.shared.domain.event.TicketAssumedNotifyEvent;
import com.marcos.estik.ticket.application.ports.in.AssumeTicketPort;
import com.marcos.estik.ticket.application.ports.out.LoadTicketCreateChatPort;
import com.marcos.estik.ticket.application.ports.out.LoadTicketCreateParticipantPort;
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
public class AssumeTicketUseCase implements AssumeTicketPort {

    private final TicketRepositoryPort ticketRepositoryPort;
    private final TicketHistoryRepositoryPort ticketHistoryRepositoryPort;
    private final LoadTicketFindFacilityByIdPort loadTicketFindFacilityByIdPort;
    private final LoadTicketFindUserByIdPort loadTicketFindUserByIdPort;
    private final LoadTicketCreateParticipantPort loadTicketCreateParticipantPort;
    private final LoadTicketCreateChatPort loadTicketCreateChatPort;
    private final TicketEventPublisherPort ticketEventPublisherPort;
    private final LoadTicketFindSpecializationByIdPort loadTicketFindSpecializationByIdPort;

    @Override
    @Transactional 
    public TicketView execute(UUID ticketId, AuthenticatedUser principal) {
        Ticket ticket = ticketRepositoryPort.findTicketById(ticketId);
        TicketHistory ticketHistory = TicketHistory.create(
            ticket.getId(), 
            StatusEnum.PROGRESS, 
            ticket.getSpecializationId()
        );

        UUID chatId = loadTicketCreateChatPort.execute();
        ticket.assignTo(principal.id(), chatId);
        loadTicketCreateParticipantPort.execute(ticket.getAssigneeId(), chatId);
        loadTicketCreateParticipantPort.execute(ticket.getRequesterId(), chatId);

        ticketRepositoryPort.save(ticket);
        ticketHistoryRepositoryPort.save(ticketHistory);
        ticketEventPublisherPort.execute(
            new TicketAssumedNotifyEvent (
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
