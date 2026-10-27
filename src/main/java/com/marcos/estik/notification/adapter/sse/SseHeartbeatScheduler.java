package com.marcos.estik.notification.adapter.sse;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class SseHeartbeatScheduler {
    
    private final SseEmitterRegistry sseEmitterRegistry;

    @Scheduled (fixedRate = 25_000)
    void ping() {
        sseEmitterRegistry.heartBeat();
    }
}
