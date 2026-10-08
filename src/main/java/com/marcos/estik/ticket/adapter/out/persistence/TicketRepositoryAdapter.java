package com.marcos.estik.ticket.adapter.out.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.application.pagination.PageResult;
import com.marcos.estik.ticket.application.ports.out.TicketRepositoryPort;
import com.marcos.estik.ticket.domain.PriorityEnum;
import com.marcos.estik.ticket.domain.StatusEnum;
import com.marcos.estik.ticket.domain.Ticket;

import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class TicketRepositoryAdapter implements TicketRepositoryPort {

    private final TicketPersistenceMapper ticketMapper;
    private final JpaTicketRepository jpaTicketRepository;

    private PageResult<Ticket> createPageResult(Page<Ticket> page) {
        return new PageResult<Ticket>(
            page.getContent(),
            page.getNumber(),
            page.getSize(),
            page.getTotalElements(),
            page.getTotalPages()
        );
    }

    @Override
    public Ticket save(Ticket ticket) {
        TicketEntity ticketEntity = ticketMapper.toEntity(ticket);
        jpaTicketRepository.save(ticketEntity);
        return ticketMapper.toDomain(ticketEntity);
    }

    @Override
    public PageResult<Ticket> SearchTicketsByPriority(String title, PriorityEnum priority, PageQuery page) {
        Page<Ticket> tickets = jpaTicketRepository.findByPriorityAndTitleContainingIgnoreCase(
            priority, 
            title, 
            PageRequest.of(page.page(), page.size())
        );

        return createPageResult(tickets);
    }

    @Override
    public PageResult<Ticket> SearchTicketsByStatus(String title, StatusEnum status, PageQuery page) {
        Page<Ticket> tickets = jpaTicketRepository.findByStatusAndTitleContainingIgnoreCase(
            status, 
            title, 
            PageRequest.of(page.page(), page.size())
        );
        return createPageResult(tickets);

    }

    @Override
    public PageResult<Ticket> SearchTickets(String title, PageQuery page) {
        Page<Ticket> tickets = jpaTicketRepository.findByTitleContainingIgnoreCase(title, PageRequest.of(page.page(), page.size()));
        return createPageResult(tickets);
    }

    
}
