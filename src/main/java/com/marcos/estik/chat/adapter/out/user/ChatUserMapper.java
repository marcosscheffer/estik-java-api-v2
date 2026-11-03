package com.marcos.estik.chat.adapter.out.user;

import org.mapstruct.Mapper;

import com.marcos.estik.chat.application.view.ChatUserInfo;
import com.marcos.estik.user.domain.User;

@Mapper(componentModel = "spring")
public interface ChatUserMapper {
    
    ChatUserInfo toUserInfo(User user);

}
