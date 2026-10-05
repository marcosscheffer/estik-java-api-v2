package com.marcos.estik.user.application.usecase;

import java.util.UUID;

import org.springframework.dao.DataIntegrityViolationException;

import com.marcos.estik.user.application.port.in.FindByIdPort;
import com.marcos.estik.user.application.port.out.UserRepositoryPort;
import com.marcos.estik.user.domain.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor 
public class FindByIdUseCase implements FindByIdPort {

    private final UserRepositoryPort userRepositoryPort;

    @Override
    public User execute(UUID id) {
        return userRepositoryPort.findById(id)
            .orElseThrow(() -> new DataIntegrityViolationException("Username not found"));
    }
    
}
