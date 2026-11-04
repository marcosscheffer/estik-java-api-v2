package com.marcos.estik.user.application.usecase;

import org.springframework.stereotype.Service;

import com.marcos.estik.shared.application.pagination.PageResult;
import com.marcos.estik.user.application.command.SearchUsersCommand;
import com.marcos.estik.user.application.port.in.SearchUsersPort;
import com.marcos.estik.user.application.port.out.UserRepositoryPort;
import com.marcos.estik.user.domain.User;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class SearchUsersUseCase implements SearchUsersPort {
    private final UserRepositoryPort userRepositoryPort;

    @Override
    public PageResult<User> execute(SearchUsersCommand command) {
        return userRepositoryPort.SearchUsers(
            command.facilityId(), 
            command.username(),
            command.role(), 
            command.page()
        );
    }

}
