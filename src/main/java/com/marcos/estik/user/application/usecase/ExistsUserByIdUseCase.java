package com.marcos.estik.user.application.usecase;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.marcos.estik.user.application.port.in.ExistsUserByIdPort;
import com.marcos.estik.user.application.port.out.UserRepositoryPort;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class ExistsUserByIdUseCase implements ExistsUserByIdPort {

    private final UserRepositoryPort userRepositoryPort;

    @Override
    public Boolean execute(UUID id) {
        return userRepositoryPort.existsById(id);
    }
}
