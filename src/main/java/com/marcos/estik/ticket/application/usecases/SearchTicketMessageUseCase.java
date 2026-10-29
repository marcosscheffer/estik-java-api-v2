package com.marcos.estik.ticket.application.usecases;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.application.pagination.PageResult;
import com.marcos.estik.ticket.application.ports.in.SearchTicketMessagePort;
import com.marcos.estik.ticket.application.ports.out.LoadTicketFindUserByIdPort;
import com.marcos.estik.ticket.application.ports.out.TicketMessageRepositoryPort;
import com.marcos.estik.ticket.application.view.TicketMessageView;
import com.marcos.estik.ticket.domain.TicketMessage;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class SearchTicketMessageUseCase implements SearchTicketMessagePort {

    private final TicketMessageRepositoryPort ticketMessageRepositoryPort;
    private final LoadTicketFindUserByIdPort loadTicketFindUserByIdPort;

    @Override
    public PageResult<TicketMessageView> execute(UUID ticketId, PageQuery page) {
        PageResult<TicketMessage> ticketMessages = ticketMessageRepositoryPort.searchTicketMessage(ticketId, page);

        return ticketMessages.map(message -> new TicketMessageView(
            message,
            loadTicketFindUserByIdPort.execute(message.getAuthorId())
        ));
    }
}
