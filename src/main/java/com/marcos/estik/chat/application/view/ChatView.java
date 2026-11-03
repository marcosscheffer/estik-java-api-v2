package com.marcos.estik.chat.application.view;

import java.util.List;

import com.marcos.estik.chat.domain.Chat;
import com.marcos.estik.shared.application.pagination.PageResult;

public record ChatView(
    Chat chat,
    List<ChatParticipantInfo> participants,
    PageResult<ChatMessageInfo> messages
) {
    
}
