package com.marcos.estik.user.application.usecase;

import org.springframework.stereotype.Service;

import com.marcos.estik.user.application.port.in.FindUserByUsernamePort;
import com.marcos.estik.user.application.port.out.UserRepositoryPort;
import com.marcos.estik.user.domain.User;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class FindUserByUsernameUseCase implements FindUserByUsernamePort {
    private final UserRepositoryPort userRepositoryPort;

    @Override
    public User execute(String username) {
        return userRepositoryPort.findByUsername(username)
            .orElseThrow(() -> new EntityNotFoundException("Username not found"));
    }
}
