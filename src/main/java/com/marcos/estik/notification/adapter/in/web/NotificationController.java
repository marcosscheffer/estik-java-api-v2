package com.marcos.estik.notification.adapter.in.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.marcos.estik.notification.adapter.in.web.mapper.NotificationWebMapper;
import com.marcos.estik.notification.adapter.in.web.response.NotificationResponse;
import com.marcos.estik.notification.adapter.in.web.response.UnreadNotificationsResponse;
import com.marcos.estik.notification.adapter.sse.SseEmitterRegistry;
import com.marcos.estik.notification.application.ports.in.CountUnreadNotificationsPort;
import com.marcos.estik.notification.application.ports.in.MarkAllReadNotificationPort;
import com.marcos.estik.notification.application.ports.in.MarkReadNotificationPort;
import com.marcos.estik.notification.application.ports.in.SearchNotificationsPort;
import com.marcos.estik.notification.domain.Notification;
import com.marcos.estik.security.AuthenticatedUser;
import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.application.pagination.PageResult;

import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.time.Duration;
import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor 
public class NotificationController {

    private final SearchNotificationsPort searchNotificationsPort;
    private final CountUnreadNotificationsPort countUnreadNotificationsPort;
    private final MarkReadNotificationPort markReadNotificationPort;
    private final MarkAllReadNotificationPort markAllReadNotificationPort;
    private final NotificationWebMapper notificationWebMapper;

    private static final Duration STREAM_TIMEOUT = Duration.ofMinutes(15);

    private final SseEmitterRegistry sseEmitterRegistry;

    @GetMapping
    public ResponseEntity<PageResult<NotificationResponse>> getNotifications(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "20") int size,
        @AuthenticationPrincipal AuthenticatedUser principal
    ) {
        PageResult<Notification> notifications = searchNotificationsPort.execute(
            principal.id(),
            new PageQuery(page, size)
        );

        return ResponseEntity.ok(notifications.map(notificationWebMapper::toSummaryResponse));
    }

    @GetMapping("/unread")
    public ResponseEntity<UnreadNotificationsResponse> getUnreadNotifications(
        @AuthenticationPrincipal AuthenticatedUser principal
    ) {
        Long quantity = countUnreadNotificationsPort.execute(principal.id());
        return ResponseEntity.ok(new UnreadNotificationsResponse(quantity));
    }

    @GetMapping(path = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter stream(
        @AuthenticationPrincipal AuthenticatedUser principal
    ) {
        SseEmitter emitter = sseEmitterRegistry.register(principal.id(), STREAM_TIMEOUT);

        Long unread = countUnreadNotificationsPort.execute(principal.id());
        try {
            emitter.send(SseEmitter.event()
                .name("unread-count")
                .data(new UnreadNotificationsResponse(unread))
            );
        } catch(IOException ex) {
            emitter.completeWithError(ex);
        }

        return emitter;
    }

    @PatchMapping("/{id}/read")
    public ResponseEntity<Void> readNotification(
        @PathVariable UUID id,
        @AuthenticationPrincipal AuthenticatedUser principal
    ) {
        markReadNotificationPort.execute(id, principal.id());
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/read")
    public ResponseEntity<UnreadNotificationsResponse> readNotifications(
        @AuthenticationPrincipal AuthenticatedUser principal
    ) {
        Long quantity = markAllReadNotificationPort.execute(principal.id());
        return ResponseEntity.ok(new UnreadNotificationsResponse(quantity));
    }    
}
