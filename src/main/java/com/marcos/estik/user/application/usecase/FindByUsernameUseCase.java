package com.marcos.estik.user.application.usecase;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.marcos.estik.user.application.command.FindByUsernameCommand;
import com.marcos.estik.user.application.port.in.FindByUsernamePort;
import com.marcos.estik.user.application.port.out.UserRepositoryPort;
import com.marcos.estik.user.domain.User;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class FindByUsernameUseCase implements FindByUsernamePort {
    private final UserRepositoryPort userRepositoryPort;

    @Override
    public User execute(FindByUsernameCommand command) {
        return userRepositoryPort.findByUsername(command.username())
            .orElseThrow(() -> new DataIntegrityViolationException("Username already exists"));
    }
}
