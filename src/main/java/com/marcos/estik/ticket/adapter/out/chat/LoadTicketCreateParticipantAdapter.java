package com.marcos.estik.ticket.adapter.out.chat;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.marcos.estik.chat.application.command.CreateParticipantCommand;
import com.marcos.estik.chat.application.ports.in.CreateParticipantPort;
import com.marcos.estik.ticket.application.ports.out.LoadTicketCreateParticipantPort;

import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class LoadTicketCreateParticipantAdapter implements LoadTicketCreateParticipantPort {
    
    private final CreateParticipantPort createParticipantPort;
    
    @Override
    public void execute(UUID userId, UUID chatId) {
        createParticipantPort.execute(new CreateParticipantCommand(chatId, userId));
    }
}
