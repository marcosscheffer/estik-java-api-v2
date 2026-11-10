package com.marcos.estik.user.application.usecase;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.marcos.estik.user.application.port.in.FindUserByRolePort;
import com.marcos.estik.user.application.port.out.UserRepositoryPort;
import com.marcos.estik.user.domain.User;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class FindUserByRoleUseCase implements FindUserByRolePort {

    private final UserRepositoryPort userRepositoryPort;

    @Override
    public List<User> execute(List<UUID> ids) {
        return userRepositoryPort.findByRoles(ids);
    }
    
}
