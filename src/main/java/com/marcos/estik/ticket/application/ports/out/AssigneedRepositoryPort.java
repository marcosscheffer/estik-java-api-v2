package com.marcos.estik.ticket.application.ports.out;

import java.util.List;
import java.util.UUID;

import com.marcos.estik.ticket.domain.Assigneed;

public interface AssigneedRepositoryPort {

    Assigneed save(Assigneed assigneed);

    List<Assigneed> searchAssigneed(UUID ticketId);

    void delete(UUID id);

    Boolean existsByTicketIdAndUserId(UUID ticketId, UUID userId);
    
}
