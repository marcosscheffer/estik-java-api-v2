package com.marcos.estik.auth.adapter.out.user;

import java.util.UUID;

import com.marcos.estik.auth.application.port.out.NewUserAccountPort;
import com.marcos.estik.auth.domain.NewUserAccount;
import com.marcos.estik.user.application.port.in.CreateUserPort;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor 
public class UserAccountAdapter implements NewUserAccountPort {

    private final CreateUserPort createUserPort;
    private final UserAccountMapper userAccountMapper;

    @Override
    public UUID createUser(NewUserAccount user) {
        return createUserPort.execute(
            userAccountMapper.toCommand(user)
        ).getId();
    }
    
}
