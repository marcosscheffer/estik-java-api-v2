package com.marcos.estik.chat.application.ports.in;

import com.marcos.estik.chat.application.command.AttachFileMessageCommand;
import com.marcos.estik.chat.application.view.ChatMessageInfo;

public interface AttachFileMessagePort {
    
    ChatMessageInfo execute(AttachFileMessageCommand command);

}
