package com.marcos.estik.chat.application.ports.out;

import java.util.UUID;

import com.marcos.estik.chat.application.view.ChatTicketInfo;

public interface LoadChatFindTicketByChatIdPort {

    ChatTicketInfo execute(UUID chatId);

}
