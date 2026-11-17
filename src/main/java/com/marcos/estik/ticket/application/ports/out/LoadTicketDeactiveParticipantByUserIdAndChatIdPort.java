package com.marcos.estik.ticket.application.ports.out;

import java.util.UUID;

public interface LoadTicketDeactiveParticipantByUserIdAndChatIdPort {
    
    void execute(UUID userId, UUID chatId);

}
