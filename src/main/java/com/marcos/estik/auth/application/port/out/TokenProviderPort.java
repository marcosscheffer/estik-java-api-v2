package com.marcos.estik.auth.application.port.out;

import java.util.UUID;

import com.marcos.estik.auth.domain.UserCredentials;
import com.marcos.estik.user.domain.RoleEnum;

public interface TokenProviderPort {

    String generateToken(UserCredentials user);

    String generateRefreshToken(UserCredentials user, UUID tokenId);

    UUID getUserId(String token);

    UUID getTokenId(String token);

    Boolean isValidRefreshToken(String token);

    Boolean isValidAccessToken(String token);

    RoleEnum getRole(String token);

    String getUsername(String token);
}
