package com.marcos.estik.user.application.usecase;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.marcos.estik.user.application.port.in.DeactiveUserByIdPort;
import com.marcos.estik.user.application.port.out.UserRepositoryPort;
import com.marcos.estik.user.domain.User;
import com.marcos.estik.user.domain.exception.CannotDeactiveSelfException;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class DeactiveUserByIdUseCase implements DeactiveUserByIdPort {

    private final UserRepositoryPort userRepositoryPort;

    @Override
    public User execute(UUID userId, UUID principalId) {
        User user = userRepositoryPort.findById(userId);
        user.deactivate(principalId);
        return userRepositoryPort.save(user);
    }
}
