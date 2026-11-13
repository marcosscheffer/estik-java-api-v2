package com.marcos.estik.ticket.adapter.out.persistence.assigneed;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaAssigneedRepository extends JpaRepository<AssigneedEntity, UUID> {

    List<AssigneedEntity> findByTicketId(UUID ticketId);

    Boolean existsByTicketIdAndUserId(UUID ticketId, UUID userId);
    
}
