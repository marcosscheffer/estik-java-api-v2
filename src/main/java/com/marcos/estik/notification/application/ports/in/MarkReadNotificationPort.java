package com.marcos.estik.notification.application.ports.in;

import java.util.UUID;

import com.marcos.estik.security.AuthenticatedUser;

public interface MarkReadNotificationPort {
    
    void execute(UUID notificationId, AuthenticatedUser principal);

}
