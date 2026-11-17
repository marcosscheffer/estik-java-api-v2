package com.marcos.estik.chat.application.ports.in;

import com.marcos.estik.chat.application.view.ChatView;
import com.marcos.estik.shared.domain.chat.ChatTypeEnum;

public interface CreateChatPort {
    ChatView execute(ChatTypeEnum type);
}
