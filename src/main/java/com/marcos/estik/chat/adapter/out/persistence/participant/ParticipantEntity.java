package com.marcos.estik.chat.adapter.out.persistence.participant;

import java.time.Instant;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity 
@Table(name = "participants")
@Getter 
@Setter 
public class ParticipantEntity {
    @Id 
    private UUID id;

    @Column(name = "chat_id")
    private UUID chatId;

    @Column(name = "user_id")
    private UUID userId;
    
    @CreationTimestamp 
    @Column(name = "joined_at", nullable = false, updatable = false)
    private Instant joinedAt;
}
