package com.marcos.estik.chat.adapter.in.web.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.marcos.estik.chat.adapter.in.web.response.ChatResponse;
import com.marcos.estik.chat.adapter.in.web.response.MessageResponse;
import com.marcos.estik.chat.adapter.in.web.response.ParticipantResponse;
import com.marcos.estik.chat.adapter.in.web.response.UserSummaryResponse;
import com.marcos.estik.chat.application.view.ChatMessageInfo;
import com.marcos.estik.chat.application.view.ChatParticipantInfo;
import com.marcos.estik.chat.application.view.ChatUserInfo;
import com.marcos.estik.chat.application.view.ChatView;

@Mapper(componentModel = "spring")
public interface ChatWebMapper {

    @Mapping(target = ".", source = "chat")
    @Mapping(target = "messages", ignore = true)
    ChatResponse toChatResponse(ChatView chat);

    MessageResponse toMessageResponse(ChatMessageInfo message);

    ParticipantResponse toParticipantResponse(ChatParticipantInfo participant);

    UserSummaryResponse toUserSummaryResponse(ChatUserInfo user);
    
}
