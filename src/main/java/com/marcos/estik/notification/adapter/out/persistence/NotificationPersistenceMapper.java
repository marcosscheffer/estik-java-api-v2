package com.marcos.estik.notification.adapter.out.persistence;

import org.mapstruct.Mapper;

import com.marcos.estik.notification.domain.Notification;

@Mapper(componentModel = "spring")
public interface NotificationPersistenceMapper {
    
    NotificationEntity toEntity(Notification notification);

    Notification toDomain(NotificationEntity notification);
}
