package com.marcos.estik.auth.application.port.out;

import java.util.Set;
import java.util.UUID;

import com.marcos.estik.auth.domain.UserAuth;
import com.marcos.estik.shared.domain.user.PermissionEnum;

public interface TokenProviderPort {

    String generateToken(UserAuth user, Set<PermissionEnum> permissions);

    String generateRefreshToken(UserAuth user, UUID tokenId);

    UUID getSpecializationId(String token);

    UUID getUserId(String token);

    UUID getTokenId(String token);

    Set<PermissionEnum> getPermission(String token);

    Boolean isValidRefreshToken(String token);

    Boolean isValidAccessToken(String token);

    String getUsername(String token);
}
