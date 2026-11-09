package com.marcos.estik.auth.application.port.out;

import java.util.Set;
import java.util.UUID;

import com.marcos.estik.auth.domain.UserAuth;
import com.marcos.estik.shared.domain.user.PermissionEnum;
import com.marcos.estik.shared.domain.user.RoleEnum;

public interface TokenProviderPort {

    String generateToken(UserAuth user, Set<PermissionEnum> permissions);

    String generateRefreshToken(UserAuth user, UUID tokenId);

    UUID getUserId(String token);

    UUID getTokenId(String token);

    Set<PermissionEnum> getPermission(String token);

    Boolean isValidRefreshToken(String token);

    Boolean isValidAccessToken(String token);

    RoleEnum getRole(String token);

    String getUsername(String token);
}
