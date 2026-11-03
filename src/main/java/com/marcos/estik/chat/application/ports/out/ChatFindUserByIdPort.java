package com.marcos.estik.chat.application.ports.out;

import java.util.UUID;

import com.marcos.estik.chat.application.view.ChatUserInfo;

public interface ChatFindUserByIdPort {
    
    ChatUserInfo execute(UUID id);
    
}
