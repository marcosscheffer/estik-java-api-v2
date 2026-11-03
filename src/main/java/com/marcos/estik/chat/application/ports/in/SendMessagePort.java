package com.marcos.estik.chat.application.ports.in;

import java.util.UUID;

import com.marcos.estik.chat.application.command.SendMessageCommand;
import com.marcos.estik.chat.application.view.ChatMessageInfo;
import com.marcos.estik.security.AuthenticatedUser;

public interface SendMessagePort {
    
    ChatMessageInfo execute(UUID chatId, SendMessageCommand command, AuthenticatedUser principal);

}
