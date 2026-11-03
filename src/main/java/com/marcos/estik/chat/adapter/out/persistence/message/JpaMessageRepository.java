package com.marcos.estik.chat.adapter.out.persistence.message;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaMessageRepository extends JpaRepository<MessageEntity, UUID> {

    Page<MessageEntity> findByChatId(UUID chatId, Pageable pageable);
    
}
