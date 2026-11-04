package com.marcos.estik.notification.adapter.out.persistence;

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
@Table(name = "notifications")
@Getter 
@Setter 
public class NotificationEntity {
    @Id 
    private UUID id;

    @Column(name = "recipient_id")
    private UUID recipientId;
    private String type;
    private String title;
    private String message;

    @Column(name = "reference_id")
    private UUID referenceId;
    @Column(name = "reference_label")
    private String referenceLabel;
    private Boolean read;

    @Column(name = "read_at", nullable = true, updatable = true)
    private Instant readAt;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;
}
