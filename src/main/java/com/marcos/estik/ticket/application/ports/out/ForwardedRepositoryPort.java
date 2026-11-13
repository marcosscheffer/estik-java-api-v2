package com.marcos.estik.ticket.application.ports.out;

import java.util.List;
import java.util.UUID;

import com.marcos.estik.ticket.domain.Forwarded;

public interface ForwardedRepositoryPort {
    
    Forwarded save(Forwarded forwarded);

    List<Forwarded> searchForwarded(UUID ticketId);

    void delete(UUID id);

    Boolean existsByTicketIdAndUserId(UUID ticketId, UUID userId);

}
