package com.marcos.estik.notification.adapter.in.sse;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;


@Component
public class SseNotificationEmitter {
    private static Map<UUID, Set<SseEmitter>> emitters = new ConcurrentHashMap<>();

    public static SseEmitter createEmitter(UUID userId) {
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
        emitters.computeIfAbsent(userId, key -> ConcurrentHashMap.newKeySet()).add(emitter);

        emitter.onCompletion(() -> remove(userId, emitter));
        emitter.onTimeout(() -> remove(userId, emitter));
        emitter.onError(error -> remove(userId, emitter));

        return emitter;
    }

    public static void sendTo(UUID userId, String name, Object payload) {
        Set<SseEmitter> userEmitters = emitters.get(userId);
        if (userEmitters == null) return;
        
        userEmitters.forEach(emitter -> send(userId, emitter, SseEmitter.event().name(name).data(payload)));
    }

    private static void send(UUID userId, SseEmitter emitter, SseEmitter.SseEventBuilder event) {
        try {
            emitter.send(event);
        } catch(IOException exception) {
            remove(userId, emitter);
        }
    }

    private static  Set<SseEmitter> remove(UUID userId, SseEmitter emitter) {
        return emitters.computeIfPresent(userId, (key, value) -> {
            value.remove(emitter);
            return value.isEmpty() ? null : value;
        });
    }
}
