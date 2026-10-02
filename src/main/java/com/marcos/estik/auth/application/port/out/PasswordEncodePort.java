package com.marcos.estik.auth.application.port.out;

public interface PasswordEncodePort {
    Boolean matches(String rawPassword, String encodedPassword);

    String encode(String rawPassword);
}
