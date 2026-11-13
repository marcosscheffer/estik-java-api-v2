package com.marcos.estik.ticket.application.ports.in;

import com.marcos.estik.ticket.application.command.CreateAssigneedCommand;
import com.marcos.estik.ticket.domain.Assigneed;

public interface CreateAssigneedPort {
    
    Assigneed execute(CreateAssigneedCommand command);

}
