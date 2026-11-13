package com.marcos.estik.ticket.adapter.out.persistence.forwarded;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaForwardedRepository extends JpaRepository<ForwardedEntity, UUID> {

    List<ForwardedEntity> findByTicketId(UUID ticketId);

    Boolean existsByTicketIdAndUserId(UUID ticketId, UUID userId);
    
}
