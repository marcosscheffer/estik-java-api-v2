package com.marcos.estik.chat.adapter.in.sse;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public class SseChatEmitter {
    private static Map<UUID, Set<SseEmitter>> emitters = new ConcurrentHashMap<>(); 

    public static SseEmitter createEmitter(UUID chatId) {
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
        emitter.onCompletion(() -> remove(chatId, emitter));
        emitter.onTimeout(() -> remove(chatId, emitter));
        emitter.onError(error -> remove(chatId, emitter));
        emitters.computeIfAbsent(chatId, key -> ConcurrentHashMap.newKeySet()).add(emitter);
        return emitter;
    }

    public static void sendTo(UUID chatId, String name, Object object) {
        Set<SseEmitter> chatEmitters = emitters.get(chatId);
        if (chatEmitters == null) return;
        if (chatEmitters.isEmpty()) return;

        chatEmitters.forEach(
            emitter -> send(chatId, emitter, SseEmitter.event().name(name).data(object))
        );
    }

    private static Set<SseEmitter> remove(UUID chatId, SseEmitter emitter) {
        return emitters.computeIfPresent(
            chatId, (key, value) -> {
                value.remove(emitter);
                return value.isEmpty() ? null : value;
            }
        );
    }

    public static void connect(UUID chatId, SseEmitter emitter) {
        send(chatId, emitter, SseEmitter.event().comment(":connect"));
    }

    public static void heartbeat() {
        emitters.forEach(
            (chatId, chatEmitters) -> chatEmitters.forEach(
                emitter -> send(chatId, emitter, SseEmitter.event().comment(":ping"))
            )
        );
    }

    private static void send(UUID chatId, SseEmitter emitter, SseEmitter.SseEventBuilder payload) {
        try {
            emitter.send(payload);
        } catch(IOException | IllegalStateException exception) {
            remove(chatId, emitter);
        }
    }
}
