package com.marcos.estik.user.application.usecase;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.marcos.estik.user.application.port.in.FindUserByRolePort;
import com.marcos.estik.user.application.port.out.LoadUserFindRoleByIdPort;
import com.marcos.estik.user.application.port.out.LoadUserFindSpecializationByIdPort;
import com.marcos.estik.user.application.port.out.UserRepositoryPort;
import com.marcos.estik.user.application.view.UserView;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class FindUserByRoleUseCase implements FindUserByRolePort {

    private final UserRepositoryPort userRepositoryPort;
    private final LoadUserFindRoleByIdPort loadUserFindRoleByIdPort;
    private final LoadUserFindSpecializationByIdPort loadUserFindSpecializationByIdPort;

    @Override
    public List<UserView> execute(List<UUID> ids) {
        return userRepositoryPort.findByRoles(ids).stream().map(
            user -> {
                return new UserView(
                    user, 
                    loadUserFindRoleByIdPort.execute(user.getRoleId()),
                    loadUserFindSpecializationByIdPort.execute(user.getSpecializationId())
                );
            }
        ).toList();
    }
    
}
