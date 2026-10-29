package com.marcos.estik.ticket.adapter.in.sse;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component 
public class TicketMessageHeartbeatScheduler {
    
    @Scheduled(fixedRate = 25000)
    public void ping() {
        SseTicketMessageEmitter.heartbeat();
    }

}
