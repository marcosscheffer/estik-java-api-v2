package com.marcos.estik.user.application.usecase;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.marcos.estik.user.application.port.in.FindUserByIdPort;
import com.marcos.estik.user.application.port.out.LoadUserFindRoleByIdPort;
import com.marcos.estik.user.application.port.out.LoadUserFindSpecializationByIdPort;
import com.marcos.estik.user.application.port.out.UserRepositoryPort;
import com.marcos.estik.user.application.view.UserView;
import com.marcos.estik.user.domain.User;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class FindUserByIdUseCase implements FindUserByIdPort {

    private final UserRepositoryPort userRepositoryPort;
    private final LoadUserFindRoleByIdPort loadUserFindRoleByIdPort;
    private final LoadUserFindSpecializationByIdPort loadUserFindSpecializationByIdPort;

    @Override
    public UserView execute(UUID id) {
        User user = userRepositoryPort.findById(id);
        return new UserView(
            user, 
            user.getRoleId() == null ? null : loadUserFindRoleByIdPort.execute(user.getRoleId()),
            user.getSpecializationId() == null ? null : loadUserFindSpecializationByIdPort.execute(user.getSpecializationId())
        );
    }
    
}
