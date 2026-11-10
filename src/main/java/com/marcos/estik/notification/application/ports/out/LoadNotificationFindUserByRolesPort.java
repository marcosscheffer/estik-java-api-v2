package com.marcos.estik.notification.application.ports.out;

import java.util.List;
import java.util.UUID;


public interface LoadNotificationFindUserByRolesPort {
    
    List<UUID> execute(List<UUID> role);

}
