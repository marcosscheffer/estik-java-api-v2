package com.marcos.estik.user.application.usecase;

import org.springframework.stereotype.Service;

import com.marcos.estik.user.application.port.in.ExistsByUsernamePort;
import com.marcos.estik.user.application.port.out.UserRepositoryPort;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class ExistsByUsernameUseCase implements ExistsByUsernamePort {
    private final UserRepositoryPort userRepositoryPort;

    @Override
    public Boolean execute(String username) {
        return userRepositoryPort.existsByUsername(username);
    }
    
}
