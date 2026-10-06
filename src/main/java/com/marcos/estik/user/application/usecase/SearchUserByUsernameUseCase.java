package com.marcos.estik.user.application.usecase;

import org.springframework.stereotype.Service;

import com.marcos.estik.user.application.command.SearchByIdCommand;
import com.marcos.estik.user.application.pagination.PageQuery;
import com.marcos.estik.user.application.pagination.PageResult;
import com.marcos.estik.user.application.port.in.SearchUserByIdPort;
import com.marcos.estik.user.application.port.out.UserRepositoryPort;
import com.marcos.estik.user.domain.User;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class SearchUserByUsernameUseCase implements SearchUserByIdPort{
    private final UserRepositoryPort userRepositoryPort;

    @Override
    public PageResult<User> execute(SearchByIdCommand command) {
        return userRepositoryPort.SearchByUsername(
            command.username(),
            new PageQuery(
                command.page(),
                command.size())
        );
    }

}
