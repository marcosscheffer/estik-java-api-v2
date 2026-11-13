package com.marcos.estik.ticket.application.ports.in;

import java.util.List;
import java.util.UUID;

import com.marcos.estik.ticket.domain.Forwarded;

public interface SearchForwardedPort {
    
    List<Forwarded> execute(UUID ticketId);

}
