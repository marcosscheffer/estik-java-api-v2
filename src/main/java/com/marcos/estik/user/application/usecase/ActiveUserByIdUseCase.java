package com.marcos.estik.user.application.usecase;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.marcos.estik.user.application.port.in.ActiveUserByIdPort;
import com.marcos.estik.user.application.port.out.UserRepositoryPort;
import com.marcos.estik.user.domain.User;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class ActiveUserByIdUseCase implements ActiveUserByIdPort {

    private final UserRepositoryPort userRepositoryPort;

    @Override
    public User execute(UUID userId) {
        User user = userRepositoryPort.findById(userId);
        user.activate();
        return userRepositoryPort.save(user);
    }
    
}
