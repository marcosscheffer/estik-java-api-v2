package com.marcos.estik.chat.application.view;

import java.util.List;

import com.marcos.estik.chat.domain.Message;

public record MessageView(
    Message message,
    MessageInfo replyTo,
    ChatParticipantInfo sender,
    List<ChatAttachmentInfo> attachments
) {
    
}
