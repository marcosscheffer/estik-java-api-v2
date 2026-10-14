package com.marcos.estik.user.application.usecase;

import org.springframework.stereotype.Service;

import com.marcos.estik.shared.domain.user.RoleEnum;
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
    public User execute(UpdateUserCommand command) {
        User user = userRepositoryPort.findById(command.getId());
        user.setEmail(command.getEmail());
        user.setUsername(command.getUsername());

        if (command.getActorRole() == RoleEnum.IT) {
            user.setFacilityId(command.getFacilityId());
            user.setRole(command.getRole());
            user.setActive(command.getActive());
        }

        userRepositoryPort.save(user);
        return user;
    }
    
}
