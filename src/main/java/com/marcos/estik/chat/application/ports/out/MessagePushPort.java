package com.marcos.estik.chat.application.ports.out;

import com.marcos.estik.chat.application.view.MessageView;

public interface MessagePushPort {
    
    void execute(MessageView message);

}
