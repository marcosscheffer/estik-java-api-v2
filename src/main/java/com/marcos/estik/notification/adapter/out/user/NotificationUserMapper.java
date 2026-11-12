package com.marcos.estik.notification.adapter.out.user;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.marcos.estik.notification.application.view.NotificationUserInfo;
import com.marcos.estik.user.application.view.UserView;

@Mapper(componentModel = "spring")
public interface NotificationUserMapper {
    
    @Mapping(target = ".", source = "user")
    NotificationUserInfo toInfo(UserView user);

}
