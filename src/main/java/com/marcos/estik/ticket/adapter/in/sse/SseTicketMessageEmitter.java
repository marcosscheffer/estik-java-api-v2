package com.marcos.estik.ticket.adapter.in.sse;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public class SseTicketMessageEmitter {
    
    private static Map<UUID, Set<SseEmitter>> emitters = new ConcurrentHashMap<>();

    public static SseEmitter createEmitter(UUID ticketId) {
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
        
        emitters.computeIfAbsent(ticketId, key -> ConcurrentHashMap.newKeySet()).add(emitter);

        emitter.onCompletion(() -> remove(ticketId, emitter));
        emitter.onTimeout(() -> remove(ticketId, emitter));
        emitter.onError(error -> remove(ticketId, emitter));

        return emitter;
    }

    public static void sendTo(UUID ticketId, String name, Object payload) {
        Set<SseEmitter> userEmitters = emitters.get(ticketId);
        if (userEmitters == null) return;
        userEmitters.forEach(emitter -> send(ticketId, emitter, SseEmitter.event().name(name).data(payload)));
    }

    private static void send(UUID ticketId, SseEmitter emitter, SseEmitter.SseEventBuilder payload) {
        try {
            emitter.send(payload);
        } catch (IOException exception) {
            remove(ticketId, emitter);
        }
    }

    private static void remove(UUID ticketId, SseEmitter emitter) {
        emitters.computeIfPresent(ticketId, (key, value) -> {
            value.remove(emitter);
            return value.isEmpty() 
                ? null
                : value;
        });
    }
}
