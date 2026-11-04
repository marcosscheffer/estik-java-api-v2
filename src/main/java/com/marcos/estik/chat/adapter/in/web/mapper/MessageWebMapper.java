package com.marcos.estik.chat.adapter.in.web.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.marcos.estik.chat.adapter.in.web.response.MessageResponse;
import com.marcos.estik.chat.application.view.MessageView;

@Mapper(componentModel = "spring")
public interface MessageWebMapper {
    
    @Mapping(target = ".", source = "message")
    MessageResponse toResponse(MessageView message);
}
