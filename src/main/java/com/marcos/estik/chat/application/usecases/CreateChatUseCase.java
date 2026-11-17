package com.marcos.estik.chat.application.usecases;

import org.springframework.stereotype.Service;

import com.marcos.estik.chat.application.ports.in.CreateChatPort;
import com.marcos.estik.chat.application.ports.out.ChatRepositoryPort;
import com.marcos.estik.chat.application.view.ChatView;
import com.marcos.estik.chat.domain.Chat;
import com.marcos.estik.shared.domain.chat.ChatTypeEnum;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class CreateChatUseCase implements CreateChatPort {

    private final ChatRepositoryPort chatRepositoryPort;

    @Override
    public ChatView execute(ChatTypeEnum type) {
        Chat chat = Chat.create(type);
        chatRepositoryPort.save(chat);
        return new ChatView(
            chat,
            null
        );
    }
}
