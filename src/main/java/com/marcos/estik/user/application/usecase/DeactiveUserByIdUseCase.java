package com.marcos.estik.user.application.usecase;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.marcos.estik.user.application.port.in.DeactiveUserByIdPort;
import com.marcos.estik.user.application.port.out.LoadUserFindRoleByIdPort;
import com.marcos.estik.user.application.port.out.LoadUserFindSpecializationByIdPort;
import com.marcos.estik.user.application.port.out.UserRepositoryPort;
import com.marcos.estik.user.application.view.UserView;
import com.marcos.estik.user.domain.User;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class DeactiveUserByIdUseCase implements DeactiveUserByIdPort {

    private final UserRepositoryPort userRepositoryPort;
    private final LoadUserFindRoleByIdPort loadUserFindRoleByIdPort;
    private final LoadUserFindSpecializationByIdPort loadUserFindSpecializationByIdPort;

    @Override
    public UserView execute(UUID userId, UUID principalId) {
        User user = userRepositoryPort.findById(userId);
        user.deactivate(principalId);
        userRepositoryPort.save(user);

        return new UserView(
            user, 
            loadUserFindRoleByIdPort.execute(user.getRoleId()),
            loadUserFindSpecializationByIdPort.execute(user.getSpecializationId())
        );
    }
}
