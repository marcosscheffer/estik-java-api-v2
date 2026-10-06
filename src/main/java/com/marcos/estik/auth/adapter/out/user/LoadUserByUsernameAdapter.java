package com.marcos.estik.auth.adapter.out.user;

import org.springframework.stereotype.Component;

import com.marcos.estik.auth.application.port.out.LoadUserByUsernamePort;
import com.marcos.estik.auth.domain.UserAuth;
import com.marcos.estik.user.application.port.in.FindUserByUsernamePort;

import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class LoadUserByUsernameAdapter implements LoadUserByUsernamePort {
    private final FindUserByUsernamePort findUserByUsernamePort;
    private final UserCredentialsMapper userCredentialsMapper;

    @Override
    public UserAuth findByUsername(String username) {
        return userCredentialsMapper.toCredentials(findUserByUsernamePort.execute(username));
    }
    
}
