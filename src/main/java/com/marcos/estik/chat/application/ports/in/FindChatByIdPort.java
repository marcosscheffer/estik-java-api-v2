package com.marcos.estik.chat.application.ports.in;

import java.util.UUID;

import com.marcos.estik.chat.application.view.ChatView;
import com.marcos.estik.security.AuthenticatedUser;

public interface FindChatByIdPort {

    ChatView execute(UUID id, AuthenticatedUser principal);

}
