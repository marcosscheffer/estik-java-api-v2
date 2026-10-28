package com.marcos.estik.ticket.adapter.out.persistence;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaTicketMessageRepository extends JpaRepository<TicketMessageEntity, UUID> {

    Page<TicketMessageEntity> findByTicketId(UUID ticketId, Pageable pageable);
    
}
