package com.marcos.estik.auth.adapter.out.user;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.marcos.estik.auth.application.port.out.CreateUserAccountPort;
import com.marcos.estik.auth.domain.NewUserAccount;
import com.marcos.estik.user.application.port.in.CreateUserPort;

import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class CreateUserAccountAdapter implements CreateUserAccountPort {

    private final CreateUserPort createUserPort;
    private final UserAccountMapper userAccountMapper;

    @Override
    public UUID createUser(NewUserAccount user) {
        return createUserPort.execute(
            userAccountMapper.toCommand(user)
        ).user().getId();
    }
    
}
