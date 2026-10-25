package com.marcos.estik.notification.adapter.in.event;

import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import com.marcos.estik.notification.application.ports.in.CreateNotificationPort;
import com.marcos.estik.shared.domain.event.TicketNotifyEvent;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component 
@Slf4j 
@RequiredArgsConstructor 
public class TicketEventListener {

    private final CreateNotificationPort createNotificationPort;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    void onTicketStatusChangedEvent(TicketNotifyEvent event) {
        createNotificationPort.execute(event);
    }
}
