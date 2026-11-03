package com.marcos.estik.chat.adapter.out.persistence.message;

import org.mapstruct.Mapper;

import com.marcos.estik.chat.domain.Message;

@Mapper(componentModel = "spring")
public interface MessagePersistenceMapper {

    MessageEntity toEntity(Message message);

    Message toDomain(MessageEntity message);
    
}
