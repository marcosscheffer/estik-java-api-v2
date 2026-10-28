package com.marcos.estik.ticket.adapter.out.persistence;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.application.pagination.PageResult;
import com.marcos.estik.ticket.application.ports.out.TicketMessageRepositoryPort;
import com.marcos.estik.ticket.domain.TicketMessage;

import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class TicketMessageRepositoryAdapter implements TicketMessageRepositoryPort {

    private final JpaTicketMessageRepository jpaTicketMessageRepository;
    private final TicketMessagePersistenceMapper ticketMessagePersistenceMapper;

    @Override
    public TicketMessage save(TicketMessage ticketMessage) {
        TicketMessageEntity ticketMessageEntity = ticketMessagePersistenceMapper.toEntity(ticketMessage);
        TicketMessageEntity TicketMessageSaved = jpaTicketMessageRepository.save(ticketMessageEntity);
        return ticketMessagePersistenceMapper.toDomain(TicketMessageSaved);
    }

    @Override
    public PageResult<TicketMessage> searchTicketMessage(UUID ticketId, PageQuery pageQuery) {
        Page<TicketMessageEntity> ticketMessages = jpaTicketMessageRepository.findByTicketId(
            ticketId, 
            PageRequest.of(pageQuery.page(), pageQuery.size())
        );

        return new PageResult<TicketMessage>(
            ticketMessages.getContent().stream().map(ticketMessagePersistenceMapper::toDomain).toList(),
            ticketMessages.getNumber(),
            ticketMessages.getSize(),
            ticketMessages.getTotalElements(),
            ticketMessages.getTotalPages()
        );
    }
}
