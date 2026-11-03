package com.marcos.estik.chat.application.ports.out;

import java.util.UUID;

import com.marcos.estik.chat.domain.Chat;

public interface ChatRepositoryPort {
    
    Chat save(Chat chat);

    Chat findById(UUID id);

}
