package com.marcos.estik.chat.adapter.out.persistence.message;

import java.time.Instant;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity 
@Table (name = "messages")
@Getter 
@Setter
public class MessageEntity {
    @Id 
    private UUID id;
    
    @Column(name = "chat_id")
    private UUID chatId;
    
    @Column(name = "sender_id")
    private UUID senderId;

    @Column(name = "reply_to_id")
    private UUID replyToId;
    private String content;

    @CreationTimestamp 
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @UpdateTimestamp 
    @Column(name = "updated_at", nullable = false, updatable = true)
    private Instant updatedAt;
}
