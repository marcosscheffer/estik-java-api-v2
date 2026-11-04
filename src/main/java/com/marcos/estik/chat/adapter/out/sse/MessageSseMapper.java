package com.marcos.estik.chat.adapter.out.sse;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.marcos.estik.chat.application.view.MessageView;

@Mapper(componentModel = "spring")
public interface MessageSseMapper {
    
    @Mapping(target = ".", source = "message")
    MessagePayload toPayload(MessageView message);
    
}
