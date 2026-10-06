package com.marcos.estik.user.application.port.in;

import com.marcos.estik.user.application.command.SearchByIdCommand;
import com.marcos.estik.user.application.pagination.PageResult;
import com.marcos.estik.user.domain.User;

public interface SearchUserByIdPort {
    PageResult<User> execute(SearchByIdCommand command);
    
}
