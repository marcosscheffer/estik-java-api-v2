package com.marcos.estik.chat.application.ports.in;

import java.util.UUID;

import com.marcos.estik.chat.domain.Participant;

public interface DeactiveParticipantByUserIdAndChatIdPort {
    
    Participant execute(UUID userId, UUID chatId);

}
