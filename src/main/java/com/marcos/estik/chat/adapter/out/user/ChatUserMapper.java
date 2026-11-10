package com.marcos.estik.chat.adapter.out.user;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.marcos.estik.chat.application.view.ChatUserInfo;
import com.marcos.estik.user.application.view.UserView;

@Mapper(componentModel = "spring")
public interface ChatUserMapper {
    
    @Mapping(target = ".", source = "user")
    ChatUserInfo toUserInfo(UserView user);

}
