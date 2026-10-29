package com.marcos.estik.notification.adapter.in.sse;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component 
public class NotificationHeartbeatScheduler {

    @Scheduled(fixedRate = 25000)
    public void ping() {
        SseNotificationEmitter.heartbeat();
    }

}
