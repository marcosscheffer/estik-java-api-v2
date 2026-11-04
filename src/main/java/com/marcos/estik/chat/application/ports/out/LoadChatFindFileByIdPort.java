package com.marcos.estik.chat.application.ports.out;

import java.util.UUID;

import com.marcos.estik.chat.application.view.ChatFileInfo;

public interface LoadChatFindFileByIdPort {
    
    ChatFileInfo execute(UUID id);

}
