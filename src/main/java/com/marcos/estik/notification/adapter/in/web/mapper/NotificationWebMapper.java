package com.marcos.estik.notification.adapter.in.web.mapper;

import org.mapstruct.Mapper;

import com.marcos.estik.notification.adapter.in.web.response.NotificationResponse;
import com.marcos.estik.notification.domain.Notification;

@Mapper(componentModel = "spring")
public interface NotificationWebMapper {
    
    NotificationResponse toSummaryResponse(Notification notification);

}
