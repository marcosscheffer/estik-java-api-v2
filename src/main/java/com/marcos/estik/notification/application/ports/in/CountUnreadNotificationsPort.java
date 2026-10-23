package com.marcos.estik.notification.application.ports.in;

import java.util.UUID;

public interface CountUnreadNotificationsPort {
    
    Long execute(UUID recipientId);

}
