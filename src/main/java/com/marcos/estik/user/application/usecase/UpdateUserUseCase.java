package com.marcos.estik.user.application.usecase;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.marcos.estik.security.AuthenticatedUser;
import com.marcos.estik.user.application.command.UpdateUserCommand;
import com.marcos.estik.user.application.port.in.UpdateUserPort;
import com.marcos.estik.user.application.port.out.UserRepositoryPort;
import com.marcos.estik.user.domain.User;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class UpdateUserUseCase implements UpdateUserPort {
    private final UserRepositoryPort userRepositoryPort;

    @Override
    public User execute(UUID userId, UpdateUserCommand command, AuthenticatedUser principal) {
        User user = userRepositoryPort.findById(userId);
        user.updateInfo(principal.id(), command.username(), command.email());
        return userRepositoryPort.save(user);
    }
}
