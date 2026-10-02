package com.marcos.estik.auth.application.usecase;

import org.springframework.stereotype.Service;

import com.marcos.estik.auth.application.command.RegisterCommand;
import com.marcos.estik.auth.application.port.in.RegisterPort;
import com.marcos.estik.auth.application.port.out.PasswordEncodePort;
import com.marcos.estik.user.application.command.CreateUserCommand;
import com.marcos.estik.user.application.mapper.UserMapper;
import com.marcos.estik.user.application.port.in.CreateUserPort;
import com.marcos.estik.user.application.result.UserData;
import com.marcos.estik.user.domain.User;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class RegisterUseCase implements RegisterPort {

    private final PasswordEncodePort passwordEncodePort;
    private final CreateUserPort createUserPort;
    private final UserMapper userMapper;

    @Override
    public UserData execute(RegisterCommand command) {
        String password = passwordEncodePort.encode(command.password());

        User user = createUserPort.execute(
            new CreateUserCommand(
                command.email(),
                command.username(),
                password
            )
        );
        
        return userMapper.toResult(user);
    }
}
