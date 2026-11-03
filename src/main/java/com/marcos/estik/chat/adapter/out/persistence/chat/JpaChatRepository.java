package com.marcos.estik.chat.adapter.out.persistence.chat;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaChatRepository extends JpaRepository<ChatEntity, UUID> {
    
}
