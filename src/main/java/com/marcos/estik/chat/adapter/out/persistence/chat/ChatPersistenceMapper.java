package com.marcos.estik.chat.adapter.out.persistence.chat;

import org.mapstruct.Mapper;

import com.marcos.estik.chat.domain.Chat;

@Mapper(componentModel = "spring")
public interface ChatPersistenceMapper {

    ChatEntity toEntity(Chat chat);

    Chat toDomain(ChatEntity chat);
    
    
}
