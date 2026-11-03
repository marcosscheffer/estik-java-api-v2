package com.marcos.estik.chat.adapter.out.persistence.chat;

import java.util.UUID;

import com.marcos.estik.chat.application.ports.out.ChatRepositoryPort;
import com.marcos.estik.chat.domain.Chat;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor 
public class ChatRepositoryAdapter implements ChatRepositoryPort {
    
    private final JpaChatRepository jpaChatRepository;
    private final ChatPersistenceMapper chatPersistenceMapper;
    
    @Override
    public Chat save(Chat chat) {
        ChatEntity chatEntity = chatPersistenceMapper.toEntity(chat);
        ChatEntity savedChatEntity = jpaChatRepository.save(chatEntity);
        return chatPersistenceMapper.toDomain(savedChatEntity);
    }

    @Override
    public Chat findById(UUID id) {
        ChatEntity chatEntity = jpaChatRepository.findById(id)
            .orElseThrow(EntityNotFoundException::new);
        return chatPersistenceMapper.toDomain(chatEntity);
    }
}
