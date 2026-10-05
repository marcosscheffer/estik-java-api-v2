package com.marcos.estik.auth.adapter.out.user;

import com.marcos.estik.auth.application.port.out.UserCredentialsPort;
import com.marcos.estik.auth.domain.UserCredentials;
import com.marcos.estik.user.application.port.in.FindByUsernamePort;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor 
public class UserCredentialsAdapter implements UserCredentialsPort {
    private final FindByUsernamePort findByUsernamePort;
    private final UserCredentialsMapper userCredentialsMapper;

    @Override
    public UserCredentials findByUsername(String username) {
        return userCredentialsMapper.toCredentials(findByUsernamePort.execute(username));
    }
    
}
