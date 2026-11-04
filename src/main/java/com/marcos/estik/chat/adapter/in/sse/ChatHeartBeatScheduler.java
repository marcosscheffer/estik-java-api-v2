package com.marcos.estik.chat.adapter.in.sse;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component 
public class ChatHeartBeatScheduler {

    @Scheduled(fixedRate = 25000)
    public void ping() {
        SseChatEmitter.heartbeat();
    }
    
}
