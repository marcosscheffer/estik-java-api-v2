package com.marcos.estik.chat.adapter.out.persistence.attachment;

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
@Table(name = "attachments")
@Getter 
@Setter 
public class AttachmentEntity {
    @Id 
    UUID id;

    @Column(name = "message_id")
    private UUID messageId;

    @Column(name = "file_id")
    private UUID fileId;

    @CreationTimestamp 
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;
}
