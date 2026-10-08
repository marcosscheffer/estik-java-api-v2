package com.marcos.estik.auth.application.port.in;

import com.marcos.estik.auth.application.result.AuthTokens;

public interface RefreshPort {
    AuthTokens execute(String token);
}
