package com.marcos.estik.chat.application.ports.out;

import java.util.List;
import java.util.UUID;

import com.marcos.estik.chat.domain.Participant;

public interface ParticipantRepositoryPort {
    
    Participant save(Participant participant);

    List<Participant> findByChatId(UUID chatId);

}
