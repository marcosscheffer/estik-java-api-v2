package com.marcos.estik.chat.application.command;

import java.util.UUID;

public record CreateParticipantCommand(
    UUID chatId, 
    UUID userId
) {
    
}
