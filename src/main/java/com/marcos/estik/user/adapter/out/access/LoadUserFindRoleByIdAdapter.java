package com.marcos.estik.user.adapter.out.access;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.marcos.estik.access.application.ports.in.FindRoleByIdPort;
import com.marcos.estik.user.application.port.out.LoadUserFindRoleByIdPort;
import com.marcos.estik.user.application.view.UserRoleInfo;

import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class LoadUserFindRoleByIdAdapter implements LoadUserFindRoleByIdPort {
    
    private final FindRoleByIdPort findRoleByIdPort;
    private final UserAccessMapper userAccessMapper;

    @Override
    public UserRoleInfo execute(UUID id) {
        return userAccessMapper.toInfo(findRoleByIdPort.execute(id));
    }
}
