package com.marcos.estik.notification.application.ports.in;

import java.util.UUID;

public interface MarkReadNotificationPort {
    
    void execute(UUID notificationId, UUID principalId);

}
