package com.marcos.estik.notification.adapter.sse;

import java.io.IOException;
import java.time.Duration;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class SseEmitterRegistry {

    private final Map<UUID, Set<SseEmitter>> emitters = new ConcurrentHashMap<>();

    public SseEmitter register(
        UUID userId,
        Duration timeout
    ) {
        SseEmitter emitter = new SseEmitter(timeout.toMillis());

        emitters.computeIfAbsent(userId, id -> ConcurrentHashMap.newKeySet()).add(emitter);

        emitter.onCompletion(() -> remove(userId, emitter));
        emitter.onTimeout(emitter::complete);
        emitter.onError(error -> remove(userId, emitter));

        return emitter;
    }

    public void sentTo(UUID userId, String eventName, Object data) {
        Set<SseEmitter> userEmitters = emitters.get(userId);
        if(userEmitters == null) return ;

        for(SseEmitter emitter : userEmitters) {
            send(userId, emitter, SseEmitter.event().name(eventName).data(data));

        }
    }

    public void heartBeat() {
        emitters.forEach((userId, userEmitters) ->
            userEmitters.forEach(emitter -> 
                send(userId, emitter, SseEmitter.event().comment("ping"))
            )
        );
    }

    private void send(UUID userId, SseEmitter emitter, SseEmitter.SseEventBuilder event) {
        try {
            emitter.send(event);
        } catch (IOException | IllegalStateException ex) {
            remove(userId, emitter);
        }
    }

    private void remove(UUID userId, SseEmitter emitter) {
        emitters.computeIfPresent(
            userId, (id, set) -> {
                set.remove(emitter);
                return set.isEmpty() ? null : set;
            }
        );
    }
}
