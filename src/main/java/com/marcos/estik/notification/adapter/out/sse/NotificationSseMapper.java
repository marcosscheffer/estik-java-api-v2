package com.marcos.estik.notification.adapter.out.sse;

import org.mapstruct.Mapper;

import com.marcos.estik.notification.domain.Notification;

@Mapper(componentModel = "spring")
public interface NotificationSseMapper {
    
    NotificationStreamPayload toPayload(Notification notification);

}
