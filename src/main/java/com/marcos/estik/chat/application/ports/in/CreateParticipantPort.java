package com.marcos.estik.chat.application.ports.in;

import com.marcos.estik.chat.application.command.CreateParticipantCommand;
import com.marcos.estik.chat.application.view.ChatParticipantInfo;

public interface CreateParticipantPort {
    
    ChatParticipantInfo execute(CreateParticipantCommand command);

}
