package com.marcos.estik.chat.application.ports.in;

import java.util.UUID;

import com.marcos.estik.chat.application.view.ChatView;

public interface FindChatByIdPort {

    ChatView execute(UUID id);

}
