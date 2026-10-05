package com.marcos.estik.auth.adapter.out.user;

import java.util.UUID;

import com.marcos.estik.auth.application.port.out.UserByIdPort;
import com.marcos.estik.auth.domain.UserAuth;
import com.marcos.estik.user.application.port.in.FindByIdPort;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor 
public class UserByIdAdapter implements UserByIdPort {

    private final FindByIdPort findByIdPort;
    private final UserMapper userMapper;

    @Override
    public UserAuth findById(UUID id) {
        return userMapper.toUserAuth(findByIdPort.execute(id));
    }
    
}
