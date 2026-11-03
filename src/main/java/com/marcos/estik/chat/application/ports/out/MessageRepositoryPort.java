package com.marcos.estik.chat.application.ports.out;

import java.util.UUID;

import com.marcos.estik.chat.domain.Message;
import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.application.pagination.PageResult;

public interface MessageRepositoryPort {

    Message save(Message message);

    Message findById(UUID id);

    PageResult<Message> SearchByChatId(UUID chatId, PageQuery page);
    
}
