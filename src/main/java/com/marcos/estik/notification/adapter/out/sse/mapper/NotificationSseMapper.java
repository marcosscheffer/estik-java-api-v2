package com.marcos.estik.notification.adapter.out.sse.mapper;

import org.mapstruct.Mapper;

import com.marcos.estik.notification.adapter.sse.NotificationStreamPayload;
import com.marcos.estik.notification.domain.Notification;

@Mapper(componentModel = "spring")
public interface NotificationSseMapper {
    
    NotificationStreamPayload toPayload(Notification notification);

}
