package com.marcos.estik.auth.application.port.out;

import java.util.UUID;

import com.marcos.estik.user.domain.RoleEnum;
import com.marcos.estik.user.domain.User;

public interface TokenProviderPort {

    String generateToken(User user);

    String generateRefreshToken(User user, UUID tokenId);

    UUID getUserId(String token);

    UUID getTokenId(String token);

    Boolean isValidRefreshToken(String token);

    Boolean isValidAccessToken(String token);

    RoleEnum getRole(String token);

    String getUsername(String token);
}
