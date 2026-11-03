package com.marcos.estik.chat.adapter.out.persistence.message;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.marcos.estik.chat.application.ports.out.MessageRepositoryPort;
import com.marcos.estik.chat.domain.Message;
import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.application.pagination.PageResult;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor 
public class MessageRepositoryAdapter implements MessageRepositoryPort {
    
    private final MessagePersistenceMapper messagePersistenceMapper;
    private final JpaMessageRepository jpaMessageRepository;
    
    @Override
    public Message save(Message message) {
        MessageEntity messageEntity = messagePersistenceMapper.toEntity(message);
        MessageEntity savedMessageEntity = jpaMessageRepository.save(messageEntity);
        return messagePersistenceMapper.toDomain(savedMessageEntity);
    }

    @Override
    public Message findById(UUID id) {
        MessageEntity messageEntity = jpaMessageRepository.findById(id)
            .orElseThrow(EntityNotFoundException::new);
        return messagePersistenceMapper.toDomain(messageEntity);
    }

    @Override
    public PageResult<Message> SearchByChatId(UUID chatId, PageQuery page) {
        Page<MessageEntity> messages = jpaMessageRepository.findByChatId(chatId, PageRequest.of(page.page(), page.size()));
        return new PageResult<Message>(
            messages.getContent().stream()
                .map(messagePersistenceMapper::toDomain)
                .toList(),
            messages.getNumber(),
            messages.getSize(),
            messages.getTotalElements(),
            messages.getTotalPages()
        );
    }
}
