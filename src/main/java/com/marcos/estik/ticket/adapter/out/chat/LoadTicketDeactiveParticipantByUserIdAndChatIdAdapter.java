package com.marcos.estik.ticket.adapter.out.chat;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.marcos.estik.chat.application.ports.in.DeactiveParticipantByUserIdAndChatIdPort;
import com.marcos.estik.ticket.application.ports.out.LoadTicketDeactiveParticipantByUserIdAndChatIdPort;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class LoadTicketDeactiveParticipantByUserIdAndChatIdAdapter implements LoadTicketDeactiveParticipantByUserIdAndChatIdPort {

    private final DeactiveParticipantByUserIdAndChatIdPort deactiveParticipantByUserIdAndChatIdPort;
    
    @Override
    public void execute(UUID userId, UUID chatId) {
        deactiveParticipantByUserIdAndChatIdPort.execute(userId, chatId);
    }
    
}
