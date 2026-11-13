package com.marcos.estik.ticket.application.ports.in;

import com.marcos.estik.ticket.application.command.CreateForwardedCommand;
import com.marcos.estik.ticket.domain.Forwarded;

public interface CreateForwardedPort {
    
    Forwarded execute(CreateForwardedCommand command);

}
