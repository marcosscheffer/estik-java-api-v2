package com.marcos.estik.user.application.usecase;

import java.util.List;

import org.springframework.stereotype.Service;

import com.marcos.estik.shared.domain.user.FunctionEnum;
import com.marcos.estik.shared.domain.user.RoleEnum;
import com.marcos.estik.user.application.port.in.FindUserByRoleAndFunctionPort;
import com.marcos.estik.user.application.port.out.UserRepositoryPort;
import com.marcos.estik.user.domain.User;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class FindUserByRoleUseCase implements FindUserByRoleAndFunctionPort {

    private final UserRepositoryPort userRepositoryPort;

    @Override
    public List<User> execute(RoleEnum role, FunctionEnum function) {
        return userRepositoryPort.findByRoleAndFunction(role, function);
    }
    
}
