package com.marcos.estik.ticket.application.ports.in;

import java.util.UUID;

public interface DeleteForwardedPort {
    
    void execute(UUID id);

}
