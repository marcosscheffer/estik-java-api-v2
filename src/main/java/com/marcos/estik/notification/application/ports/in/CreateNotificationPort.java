package com.marcos.estik.notification.application.ports.in;

import com.marcos.estik.shared.domain.event.TicketNotifyEvent;

public interface CreateNotificationPort {

    void execute(TicketNotifyEvent event);
}
