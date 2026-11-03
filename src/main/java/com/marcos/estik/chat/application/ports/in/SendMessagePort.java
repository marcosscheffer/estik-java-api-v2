package com.marcos.estik.chat.application.ports.in;

import com.marcos.estik.chat.application.command.SendMessageCommand;
import com.marcos.estik.chat.application.view.ChatMessageInfo;

public interface SendMessagePort {
    
    ChatMessageInfo execute(SendMessageCommand command);

}
