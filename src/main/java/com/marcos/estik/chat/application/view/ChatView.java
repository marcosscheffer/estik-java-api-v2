package com.marcos.estik.chat.application.view;

import java.util.List;

import com.marcos.estik.chat.domain.Chat;

public record ChatView(
    Chat chat,
    List<ChatParticipantInfo> participants
) {
    
}
