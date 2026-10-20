package com.marcos.estik.user.application.usecase;

import org.springframework.stereotype.Service;

import com.marcos.estik.user.application.command.CreateUserCommand;
import com.marcos.estik.user.application.port.in.CreateUserPort;
import com.marcos.estik.user.application.port.out.LoadUserExistsFacilityByIdPort;
import com.marcos.estik.user.application.port.out.UserRepositoryPort;
import com.marcos.estik.user.domain.User;
import com.marcos.estik.user.domain.exception.UsernameAlreadyUseException;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class CreateUserUseCase implements CreateUserPort {

    private final UserRepositoryPort userRepositoryPort;
    private final LoadUserExistsFacilityByIdPort loadUserExistsFacilityByIdPort;

    @Override
    public User execute(CreateUserCommand command) {
        if (userRepositoryPort.existsByUsername(command.username())) {
            throw new UsernameAlreadyUseException("Username already exists");
        }
        if (!loadUserExistsFacilityByIdPort.existsById(command.facility())) {
            throw new EntityNotFoundException("Facility not found");
        }

        User user = User.create(
                command.username(), 
                command.email(), 
                command.password(),
                command.facility()
        );

        userRepositoryPort.save(user);

        return user;
    }
    
}
