package com.marcos.estik.ticket.adapter.out.persistence;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.marcos.estik.shared.application.pagination.PageResult;
import com.marcos.estik.ticket.application.command.SearchTicketsFilter;
import com.marcos.estik.ticket.application.ports.out.TicketRepositoryPort;
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
        TicketEntity ticketSaved = jpaTicketRepository.save(ticketEntity);
        return ticketPersistenceMapper.toDomain(ticketSaved);
    }

    @Override
    public PageResult<Ticket> searchTickets(SearchTicketsFilter filter) {

        Specification<TicketEntity> spec = Specification.allOf(
            TicketSpecification.titleContains(filter.title()),
            TicketSpecification.facilityIdIs(filter.facilityId()),
            TicketSpecification.requesterIdIs(filter.requesterId()),
            TicketSpecification.forwardedIdIs(filter.forwardedId()),
            TicketSpecification.regulatorIs(filter.regulatorId()),
            TicketSpecification.assigneeIs(filter.assigneeId()),
            TicketSpecification.priorityIs(filter.priority()),
            TicketSpecification.specializationIs(filter.specializationId()),
            TicketSpecification.statusIs(filter.status()),

            TicketSpecification.FilterIs(filter.visibleUserId(), filter.visibleSpecializationId())
        );

        Page<TicketEntity> tickets = jpaTicketRepository.findAll(
            spec, 
            PageRequest.of(filter.page().page(), filter.page().size(), Sort.by(Sort.Direction.DESC, "createdAt"))
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

    @Override
    public void deleteTicketById(UUID id) {
        jpaTicketRepository.deleteById(id);
    }
}
