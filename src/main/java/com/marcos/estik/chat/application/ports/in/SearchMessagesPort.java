package com.marcos.estik.chat.application.ports.in;

import java.util.UUID;

import com.marcos.estik.chat.application.view.MessageView;
import com.marcos.estik.security.AuthenticatedUser;
import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.application.pagination.PageResult;

public interface SearchMessagesPort {
    
    PageResult<MessageView> execute(UUID chatId, AuthenticatedUser principal, PageQuery page);

}
