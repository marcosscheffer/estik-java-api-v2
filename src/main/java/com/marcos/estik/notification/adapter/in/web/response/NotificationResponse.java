package com.marcos.estik.notification.adapter.in.web.response;

import java.time.Instant;
import java.util.UUID;

public record NotificationResponse(
    UUID id,
    String title,
    String message,
    Boolean read,
    String type,
    UUID referenceId,
    Instant createdAt
) {
    
}
