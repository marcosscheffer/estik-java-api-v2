package com.marcos.estik.user.application.usecase;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.marcos.estik.user.application.command.CreateUserCommand;
import com.marcos.estik.user.application.port.in.CreateUserPort;
import com.marcos.estik.user.application.port.out.UserRepositoryPort;
import com.marcos.estik.user.domain.User;
import com.marcos.estik.user.domain.exception.UsernameAlreadyUseException;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class CreateUserUseCase implements CreateUserPort {

    private final UserRepositoryPort userRepositoryPort;

    @Override
    public User execute(CreateUserCommand command) {
        if (userRepositoryPort.ExistsByUsername(command.username())) {
            throw new UsernameAlreadyUseException("Username already exists");
        }

        User user = User.create(
                command.username(), 
                command.email(), 
                command.password()
        );


        userRepositoryPort.save(user);

        return user;
    }
    
}
