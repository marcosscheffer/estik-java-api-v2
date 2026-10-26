package com.marcos.estik.notification.application.command;

import java.util.List;
import java.util.UUID;

public record CreateNotificationCommand(
    List<UUID> recipientsId,
    UUID referenceId,
    String type,
    String title,
    String message,
    UUID actorId
) {
    
}
