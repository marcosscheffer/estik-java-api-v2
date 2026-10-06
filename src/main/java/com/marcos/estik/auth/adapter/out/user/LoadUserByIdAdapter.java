package com.marcos.estik.auth.adapter.out.user;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.marcos.estik.auth.application.port.out.LoadUserByIdPort;
import com.marcos.estik.auth.domain.UserAuth;
import com.marcos.estik.user.application.port.in.FindUserByIdPort;

import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class LoadUserByIdAdapter implements LoadUserByIdPort {

    private final FindUserByIdPort findByIdPort;
    private final UserMapper userMapper;

    @Override
    public UserAuth findById(UUID id) {
        return userMapper.toUserAuth(findByIdPort.execute(id));
    }
    
}
