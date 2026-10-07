package com.marcos.estik.auth.application.usecase;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.marcos.estik.auth.application.command.RegisterCommand;
import com.marcos.estik.auth.application.port.in.RegisterPort;
import com.marcos.estik.auth.application.port.out.CreateUserAccountPort;
import com.marcos.estik.auth.application.port.out.PasswordEncodePort;
import com.marcos.estik.auth.application.port.out.LoadUserByIdPort;
import com.marcos.estik.auth.domain.NewUserAccount;
import com.marcos.estik.auth.domain.UserAuth;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class RegisterUseCase implements RegisterPort {

    private final PasswordEncodePort passwordEncodePort;
    private final CreateUserAccountPort createUserAccountPort;
    private final LoadUserByIdPort loadUserByIdPort;

    @Override
    public UserAuth execute(RegisterCommand command) {
        String password = passwordEncodePort.encode(command.password());

        UUID userId = createUserAccountPort.createUser(
            new NewUserAccount(
                command.username(),
                command.email(),
                password,
                command.facility()
            )
        );

        return loadUserByIdPort.findById(userId);
    }
}
