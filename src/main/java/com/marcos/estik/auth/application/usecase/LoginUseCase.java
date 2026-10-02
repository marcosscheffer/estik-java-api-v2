package com.marcos.estik.auth.application.usecase;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.marcos.estik.auth.application.command.LoginCommand;
import com.marcos.estik.auth.application.port.in.LoginPort;
import com.marcos.estik.auth.application.port.out.PasswordEncodePort;
import com.marcos.estik.auth.application.port.out.RefreshTokenRepositoryPort;
import com.marcos.estik.auth.application.port.out.TokenProviderPort;
import com.marcos.estik.auth.application.result.AuthTokens;
import com.marcos.estik.auth.domain.RefreshToken;
import com.marcos.estik.auth.domain.exception.InvalidCredentialsException;
import com.marcos.estik.auth.domain.exception.UserInactiveException;
import com.marcos.estik.user.application.port.out.UserRepositoryPort;
import com.marcos.estik.user.domain.User;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class LoginUseCase implements LoginPort {

    private final UserRepositoryPort userRepository;
    private final PasswordEncodePort passwordEncoder;
    private final TokenProviderPort tokenProvider;
    private final RefreshTokenRepositoryPort refreshTokenRepository;

    @Override
    public AuthTokens execute(LoginCommand command) {
        User user = userRepository.findByUsername(command.username())
            .orElseThrow(() -> new InvalidCredentialsException("Invalid username or password"));

        if (!user.getActive()) {
            throw new UserInactiveException();
        }

        if (!passwordEncoder.matches(command.password(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid username or password");
        }

        String accessToken = tokenProvider.generateToken(user);

        UUID tokenId = UUID.randomUUID();

        String refreshToken = tokenProvider.generateRefreshToken(user, tokenId);
        refreshTokenRepository.save(RefreshToken.create(tokenId, user.getId()));

        return new AuthTokens(accessToken, refreshToken);
    }
    
}
