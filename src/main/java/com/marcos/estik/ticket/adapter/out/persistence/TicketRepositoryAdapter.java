package com.marcos.estik.ticket.adapter.out.persistence;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
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

    @Override
    public Ticket save(Ticket ticket) {
        TicketEntity ticketEntity = ticketPersistenceMapper.toEntity(ticket);
        jpaTicketRepository.save(ticketEntity);
        return ticketPersistenceMapper.toDomain(ticketEntity);
    }

    @Override
    public PageResult<Ticket> searchTickets(
        String title,
        UUID facilityId,
        UUID requesterId,
        UUID assigneeId,
        PriorityEnum priority,
        StatusEnum status,
        PageQuery page
    ) {

        Specification<TicketEntity> spec = Specification.allOf(
            TicketSpecification.titleContains(title),
            TicketSpecification.facilityIs(facilityId),
            TicketSpecification.requesterIs(requesterId),
            TicketSpecification.assigneeIs(assigneeId),
            TicketSpecification.priorityIs(priority),
            TicketSpecification.statusIs(status),
            TicketSpecification.priorityIs(priority)
        );

        Page<TicketEntity> tickets = jpaTicketRepository.findAll(
            spec, 
            PageRequest.of(page.page(), page.size(), Sort.by("createdAt"))
        );

        return new PageResult<Ticket>(
            tickets.getContent().stream().map(ticketPersistenceMapper::toDomain).toList(),
            tickets.getNumber(),
            tickets.getSize(),
            tickets.getTotalElements(),
            tickets.getTotalPages()
        );
    }

    @Override
    public Ticket findTicketById(UUID id) {
        return ticketPersistenceMapper.toDomain(jpaTicketRepository.findById(id).orElseThrow(
            () -> new EntityNotFoundException("Ticket not found")
        ));
    }
}
