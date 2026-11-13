package com.marcos.estik.ticket.application.ports.in;

import java.util.UUID;

public interface DeleteAssigneedPort {

    void execute(UUID id);
    
}
