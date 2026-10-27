package com.marcos.estik.notification.application.ports.out;

import java.util.List;

import com.marcos.estik.notification.domain.Notification;

public interface NotificationPushPort {

    void push(List<Notification> notifications);
    
} 
