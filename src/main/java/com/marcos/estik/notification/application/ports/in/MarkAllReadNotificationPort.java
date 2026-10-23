package com.marcos.estik.notification.application.ports.in;

import java.util.UUID;

public interface MarkAllReadNotificationPort {
    
    int execute(UUID recipientId);

}
