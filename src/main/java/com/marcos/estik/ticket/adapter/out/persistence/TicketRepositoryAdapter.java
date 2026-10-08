package com.marcos.estik.ticket.adapter.out.persistence;

import org.springframework.stereotype.Component;

import com.marcos.estik.ticket.application.ports.out.TicketRepositoryPort;
import com.marcos.estik.ticket.domain.Ticket;

import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class TicketRepositoryAdapter implements TicketRepositoryPort {

    private final TicketPersistenceMapper ticketMapper;
    private final JpaTicketRepository jpaTicketRepository;

    @Override
    public Ticket save(Ticket ticket) {
        TicketEntity ticketEntity = ticketMapper.toEntity(ticket);
        jpaTicketRepository.save(ticketEntity);
        return ticketMapper.toDomain(ticketEntity);
    }
    
}
