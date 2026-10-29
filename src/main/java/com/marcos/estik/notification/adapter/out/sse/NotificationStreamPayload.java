package com.marcos.estik.notification.adapter.out.sse;

import java.time.Instant;
import java.util.UUID;

public record NotificationStreamPayload( 
    UUID id,
    String type,
    String title,
    String message,
    UUID referencesId,
    Instant createdAt
) {
}
