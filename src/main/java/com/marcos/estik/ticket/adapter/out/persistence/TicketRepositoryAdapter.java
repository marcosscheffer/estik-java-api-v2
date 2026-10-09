package com.marcos.estik.ticket.adapter.out.persistence;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.application.pagination.PageResult;
import com.marcos.estik.ticket.application.ports.out.TicketRepositoryPort;
import com.marcos.estik.ticket.domain.PriorityEnum;
import com.marcos.estik.ticket.domain.StatusEnum;
import com.marcos.estik.ticket.domain.Ticket;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class TicketRepositoryAdapter implements TicketRepositoryPort {

    private final TicketPersistenceMapper ticketPersistenceMapper;
    private final JpaTicketRepository jpaTicketRepository;

    private PageResult<Ticket> createPageResult(Page<TicketEntity> page) {
        return new PageResult<Ticket>(
            page.getContent().stream().map(ticketPersistenceMapper::toDomain).toList(),
            page.getNumber(),
            page.getSize(),
            page.getTotalElements(),
            page.getTotalPages()
        );
    }

    @Override
    public Ticket save(Ticket ticket) {
        TicketEntity ticketEntity = ticketPersistenceMapper.toEntity(ticket);
        jpaTicketRepository.save(ticketEntity);
        return ticketPersistenceMapper.toDomain(ticketEntity);
    }

    @Override
    public PageResult<Ticket> searchTicketsByPriority(String title, PriorityEnum priority, PageQuery page) {
        Page<TicketEntity> tickets = jpaTicketRepository.findByPriorityAndTitleContainingIgnoreCase(
            priority, 
            title, 
            PageRequest.of(page.page(), page.size())
        );

        return createPageResult(tickets);
    }

    @Override
    public PageResult<Ticket> searchTicketsByStatus(String title, StatusEnum status, PageQuery page) {
        Page<TicketEntity> tickets = jpaTicketRepository.findByStatusAndTitleContainingIgnoreCase(
            status, 
            title, 
            PageRequest.of(page.page(), page.size())
        );
        return createPageResult(tickets);

    }

    @Override
    public PageResult<Ticket> searchTickets(String title, PageQuery page) {
        Page<TicketEntity> tickets = jpaTicketRepository.findByTitleContainingIgnoreCase(title, PageRequest.of(page.page(), page.size()));
        return createPageResult(tickets);
    }

    @Override
    public Ticket findTicketById(UUID id) {
        return ticketPersistenceMapper.toDomain(jpaTicketRepository.findById(id).orElseThrow(
            () -> new EntityNotFoundException("Ticket not found")
        ));
    }

    @Override
    public PageResult<Ticket> searchTicketsByFacility(UUID userId, PageQuery page) {
        Page<TicketEntity> tickets = jpaTicketRepository.findByFacilityId(
            userId, 
            PageRequest.of(page.page(), page.size())
        );

        return createPageResult(tickets);
    }
}
