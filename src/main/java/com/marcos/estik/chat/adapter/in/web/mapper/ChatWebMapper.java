package com.marcos.estik.chat.adapter.in.web.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.marcos.estik.chat.adapter.in.web.response.ChatResponse;
import com.marcos.estik.chat.application.view.ChatView;

@Mapper(componentModel = "spring")
public interface ChatWebMapper {

    @Mapping(target = ".", source = "chat")
    ChatResponse toResponse(ChatView chat);
    
}
