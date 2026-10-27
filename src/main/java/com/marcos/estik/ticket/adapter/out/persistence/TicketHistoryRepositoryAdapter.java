package com.marcos.estik.ticket.adapter.out.persistence;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.application.pagination.PageResult;
import com.marcos.estik.ticket.application.ports.out.TicketHistoryRepositoryPort;
import com.marcos.estik.ticket.domain.StatusEnum;
import com.marcos.estik.ticket.domain.TicketHistory;
import com.marcos.estik.ticket.domain.TicketTypeEnum;

import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class TicketHistoryRepositoryAdapter implements TicketHistoryRepositoryPort {
    
    private final TicketHistoryPersistenceMapper ticketHistoryPersistenceMapper;
    private final JpaTicketHistoryRepository jpaTicketHistoryRepository;
    
    @Override
    public TicketHistory save(TicketHistory ticketHistory) {
        TicketHistoryEntity ticketHistoryEntity = ticketHistoryPersistenceMapper.toEntity(ticketHistory);
        TicketHistoryEntity ticketHistorySaved = jpaTicketHistoryRepository.save(ticketHistoryEntity);
        return ticketHistoryPersistenceMapper.toDomain(ticketHistorySaved);
    }

    @Override
    public PageResult<TicketHistory> searchTicketHistory(UUID ticketId, StatusEnum status, TicketTypeEnum type,
            PageQuery page) {
        Specification<TicketHistoryEntity> spec = Specification.allOf(
            TicketHistorySpecification.typeIs(type),
            TicketHistorySpecification.statusIs(status),
            TicketHistorySpecification.ticketIs(ticketId)
        );

        Page<TicketHistoryEntity> ticketHistories = jpaTicketHistoryRepository.findAll(
            spec, 
            PageRequest.of(page.page(), page.size(), Sort.by(Sort.Direction.DESC, "createdAt"))
        );

        return new PageResult<TicketHistory>(
            ticketHistories.getContent().stream().map(ticketHistoryPersistenceMapper::toDomain).toList(),
            ticketHistories.getNumber(),
            ticketHistories.getSize(),
            ticketHistories.getTotalElements(),
            ticketHistories.getTotalPages()
        );
    }
}
