package com.marcos.estik.notification.application.command;

import java.util.UUID;

public record CreateNotificationCommand(
    UUID recipientId,
    UUID actorId,
    String type,
    String title,
    String message,
    UUID referencesId
) {
    
}
