package com.marcos.estik.ticket.application.usecases;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.marcos.estik.security.AuthenticatedUser;
import com.marcos.estik.shared.domain.event.TicketMessageEvent;
import com.marcos.estik.ticket.application.ports.in.SendTicketMessagePort;
import com.marcos.estik.ticket.application.ports.out.LoadTicketFindUserByIdPort;
import com.marcos.estik.ticket.application.ports.out.TicketEventPublisherPort;
import com.marcos.estik.ticket.application.ports.out.TicketMessageRepositoryPort;
import com.marcos.estik.ticket.application.ports.out.TicketRepositoryPort;
import com.marcos.estik.ticket.application.view.TicketMessageView;
import com.marcos.estik.ticket.domain.Ticket;
import com.marcos.estik.ticket.domain.TicketMessage;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class SendTicketMessageUseCase implements SendTicketMessagePort {

    private final TicketRepositoryPort ticketRepositoryPort;
    private final TicketMessageRepositoryPort ticketMessageRepositoryPort;
    private final LoadTicketFindUserByIdPort loadTicketFindUserByIdPort;
    private final TicketEventPublisherPort ticketEventPublisherPort;

    @Override
    public TicketMessageView execute(UUID ticketId, String body, AuthenticatedUser principal) {
        Ticket ticket = ticketRepositoryPort.findTicketById(ticketId);
        ticket.ensureCanSendMessage(principal.id());
        TicketMessage ticketMessage = TicketMessage.create(ticketId, principal.id(), body);
        ticketMessageRepositoryPort.save(ticketMessage);
        TicketMessageView ticketMessageView = new TicketMessageView(
            ticketMessage,
            loadTicketFindUserByIdPort.execute(principal.id())
        );

        ticketEventPublisherPort.execute(
            new TicketMessageEvent(
                ticketMessage.getId(),
                ticket.getId(),
                ticket.getTitle(), 
                ticket.getRequesterId(), 
                ticket.getAssigneeId(), 
                principal.id(), 
                ticket.getTicketType().name(), 
                ticketMessage.getCreatedAt()
            )
        );

        return ticketMessageView;
    }
    
}
