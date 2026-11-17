package com.marcos.estik.chat.adapter.out.ticket;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.marcos.estik.chat.application.ports.out.LoadChatFindTicketByChatIdPort;
import com.marcos.estik.chat.application.view.ChatTicketInfo;
import com.marcos.estik.ticket.application.ports.in.FindTicketByChatIdPort;

import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class LoadChatFindTicketByChatIdAdapter implements LoadChatFindTicketByChatIdPort {
    
    private final FindTicketByChatIdPort findTicketByChatIdPort;
    private final ChatTicketMapper chatTicketMapper;
    
    @Override
    public ChatTicketInfo execute(UUID chatId) {
        return chatTicketMapper.toInfo(findTicketByChatIdPort.execute(chatId));
    }
    
}
