package com.marcos.estik.user.application.port.in;

import com.marcos.estik.shared.application.pagination.PageResult;
import com.marcos.estik.user.application.command.SearchUsersCommand;
import com.marcos.estik.user.domain.User;

public interface SearchUsersPort {
    PageResult<User> execute(SearchUsersCommand command);
}
