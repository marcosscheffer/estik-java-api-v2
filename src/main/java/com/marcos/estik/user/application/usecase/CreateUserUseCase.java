package com.marcos.estik.user.application.usecase;

import org.springframework.stereotype.Service;

import com.marcos.estik.user.application.command.CreateUserCommand;
import com.marcos.estik.user.application.port.in.CreateUserPort;
import com.marcos.estik.user.application.port.out.LoadUserFindRoleByIdPort;
import com.marcos.estik.user.application.port.out.LoadUserFindSpecializationByIdPort;
import com.marcos.estik.user.application.port.out.UserRepositoryPort;
import com.marcos.estik.user.application.view.UserView;
import com.marcos.estik.user.domain.User;
import com.marcos.estik.user.domain.exception.UsernameAlreadyUseException;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class CreateUserUseCase implements CreateUserPort {

    private final UserRepositoryPort userRepositoryPort;
    private final LoadUserFindRoleByIdPort loadUserFindRoleByIdPort;
    private final LoadUserFindSpecializationByIdPort loadUserFindSpecializationByIdPort;


    @Override
    public UserView execute(CreateUserCommand command) {
        if (userRepositoryPort.existsByUsername(command.username())) {
            throw new UsernameAlreadyUseException("Username already exists");
        }

        User user = User.create(
                command.username(), 
                command.email(), 
                command.password(),
                null,
                null
        );
        userRepositoryPort.save(user);
        
        return new UserView(
            user, 
            loadUserFindRoleByIdPort.execute(user.getRoleId()),
            loadUserFindSpecializationByIdPort.execute(user.getSpecializationId())
        );
    }
    
}
