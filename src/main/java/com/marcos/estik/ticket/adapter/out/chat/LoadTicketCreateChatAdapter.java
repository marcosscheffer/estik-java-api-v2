package com.marcos.estik.ticket.adapter.out.chat;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.marcos.estik.chat.application.ports.in.CreateChatPort;
import com.marcos.estik.shared.domain.chat.ChatTypeEnum;
import com.marcos.estik.ticket.application.ports.out.LoadTicketCreateChatPort;

import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class LoadTicketCreateChatAdapter implements LoadTicketCreateChatPort {

    private final CreateChatPort createChatPort;
    
    @Override
    public UUID execute() {
        return createChatPort.execute(ChatTypeEnum.TICKET).chat().getId();
    }
}
