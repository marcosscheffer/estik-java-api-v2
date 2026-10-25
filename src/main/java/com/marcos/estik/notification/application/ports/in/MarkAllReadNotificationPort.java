package com.marcos.estik.notification.application.ports.in;

import java.util.UUID;


public interface MarkAllReadNotificationPort {
    
    Long execute(UUID recipientId);

}
