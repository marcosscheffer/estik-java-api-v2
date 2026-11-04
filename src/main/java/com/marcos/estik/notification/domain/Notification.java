package com.marcos.estik.notification.domain;

import java.time.Instant;
import java.util.UUID;

import com.marcos.estik.notification.domain.exception.NotificationNotFoundException;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter 
@AllArgsConstructor 
public class Notification {
    private UUID id;
    private UUID recipientId;
    private String type;
    private String title;
    private String message;
    private UUID referenceId;
    private String referenceLabel;
    private Boolean read;
    private Instant readAt;
    private UUID actorId;
    private Instant createdAt;

    public static Notification create(
        UUID recipientId,
        String type,
        String title,
        String message,
        UUID referenceId,
        String referenceLabel,
        UUID actorId
    ) {
        return new Notification(
            UUID.randomUUID(),
            recipientId, 
            type, 
            title, 
            message,
            referenceId,
            referenceLabel,
            false, 
            null,
            actorId, 
            Instant.now()
        );
    }

    public void markAsRead(UUID actorId) {
        if (!actorId.equals(this.recipientId)) {
            throw new NotificationNotFoundException("Notification not found");
        }

        if(this.read) return;
        
        this.read = true;
        this.readAt = Instant.now();
    }
    
}
