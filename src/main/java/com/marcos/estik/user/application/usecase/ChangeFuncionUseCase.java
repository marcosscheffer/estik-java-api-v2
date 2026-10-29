package com.marcos.estik.user.application.usecase;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.marcos.estik.security.AuthenticatedUser;
import com.marcos.estik.shared.domain.user.FunctionEnum;
import com.marcos.estik.user.application.port.in.ChangeFunctionPort;
import com.marcos.estik.user.application.port.out.UserRepositoryPort;
import com.marcos.estik.user.domain.User;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class ChangeFuncionUseCase implements ChangeFunctionPort {

    private final UserRepositoryPort userRepositoryPort;
    
    @Override
    public User execute(UUID id, FunctionEnum function, AuthenticatedUser principal) {
        User user = userRepositoryPort.findById(id);
        user.changeFunction(principal.id(), function);
        userRepositoryPort.save(user);
        return user;
    }
}
