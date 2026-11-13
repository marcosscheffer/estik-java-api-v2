package com.marcos.estik.ticket.application.ports.in;

import java.util.List;
import java.util.UUID;

import com.marcos.estik.ticket.domain.Assigneed;

public interface SearchAssigneedPort {
    
    List<Assigneed> execute(UUID ticketId);

}
