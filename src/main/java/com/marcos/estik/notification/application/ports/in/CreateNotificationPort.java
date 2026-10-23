package com.marcos.estik.notification.application.ports.in;

import com.marcos.estik.notification.application.command.CreateNotificationCommand;

public interface CreateNotificationPort {

    void execute(
        CreateNotificationCommand command
    );
}
