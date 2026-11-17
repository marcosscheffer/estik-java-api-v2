package com.marcos.estik.chat.adapter.out.persistence.chat;

import java.time.Instant;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import com.marcos.estik.shared.domain.chat.ChatTypeEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity 
@Table(name = "chats")
@Getter 
@Setter
public class ChatEntity {
    @Id
    private UUID id;

    @Enumerated(EnumType.STRING)
    private ChatTypeEnum type;

    @CreationTimestamp 
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;
}
