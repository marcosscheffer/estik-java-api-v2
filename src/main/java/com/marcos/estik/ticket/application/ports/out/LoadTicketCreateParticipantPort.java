package com.marcos.estik.ticket.application.ports.out;

import java.util.UUID;

public interface LoadTicketCreateParticipantPort {
    
    void execute(UUID userId, UUID chatId);
    
}
