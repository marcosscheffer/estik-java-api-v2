package com.marcos.estik.auth.application.usecase;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.marcos.estik.auth.application.command.LoginCommand;
import com.marcos.estik.auth.application.port.in.LoginPort;
import com.marcos.estik.auth.application.port.out.PasswordEncodePort;
import com.marcos.estik.auth.application.port.out.RefreshTokenRepositoryPort;
import com.marcos.estik.auth.application.port.out.TokenProviderPort;
import com.marcos.estik.auth.application.port.out.LoadUserByUsernamePort;
import com.marcos.estik.auth.application.result.AuthTokens;
import com.marcos.estik.auth.domain.RefreshToken;
import com.marcos.estik.auth.domain.UserAuth;
import com.marcos.estik.auth.domain.exception.InvalidCredentialsException;
import com.marcos.estik.auth.domain.exception.UserInactiveException;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class LoginUseCase implements LoginPort {

    private final PasswordEncodePort passwordEncoder;
    private final LoadUserByUsernamePort loadUserByUsernamePort;
    private final TokenProviderPort tokenProvider;
    private final RefreshTokenRepositoryPort refreshTokenRepository;

    @Override
    public AuthTokens execute(LoginCommand command) {
        UserAuth user = loadUserByUsernamePort.findByUsername(command.username());

        if (!user.getActive()) {
            throw new UserInactiveException();
        }

        if (!passwordEncoder.matches(command.password(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid username or password");
        }

        String accessToken = tokenProvider.generateToken(user);
        
        UUID tokenId = UUID.randomUUID();
        RefreshToken token = RefreshToken.create(tokenId, user.getId());
        refreshTokenRepository.save(token);
        String refreshToken = tokenProvider.generateRefreshToken(user, token.getId());

        return new AuthTokens(accessToken, refreshToken);
    }
    
}
