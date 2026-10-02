package com.marcos.estik.user.application.usecase;

import org.springframework.stereotype.Service;

import com.marcos.estik.user.application.command.ExistsByUsernameCommand;
import com.marcos.estik.user.application.port.in.ExistsByUsernamePort;
import com.marcos.estik.user.application.port.out.UserRepositoryPort;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class ExistsByUsernameUseCase implements ExistsByUsernamePort {
    private final UserRepositoryPort userRepositoryPort;

    @Override
    public Boolean execute(ExistsByUsernameCommand command) {
        return userRepositoryPort.ExistsByUsername(command.username());
    }
    
}
