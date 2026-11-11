package com.marcos.estik.user.application.usecase;

import org.springframework.stereotype.Service;

import com.marcos.estik.user.application.port.in.FindUserByUsernamePort;
import com.marcos.estik.user.application.port.out.LoadUserFindRoleByIdPort;
import com.marcos.estik.user.application.port.out.LoadUserFindSpecializationByIdPort;
import com.marcos.estik.user.application.port.out.UserRepositoryPort;
import com.marcos.estik.user.application.view.UserView;
import com.marcos.estik.user.domain.User;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class FindUserByUsernameUseCase implements FindUserByUsernamePort {

    private final UserRepositoryPort userRepositoryPort;
    private final LoadUserFindRoleByIdPort loadUserFindRoleByIdPort;
    private final LoadUserFindSpecializationByIdPort loadUserFindSpecializationByIdPort;

    @Override
    public UserView execute(String username) {
        User user = userRepositoryPort.findByUsername(username);
        return new UserView(
            user, 
            loadUserFindRoleByIdPort.execute(user.getRoleId()),
            loadUserFindSpecializationByIdPort.execute(user.getSpecializationId())
        );
    }
}
