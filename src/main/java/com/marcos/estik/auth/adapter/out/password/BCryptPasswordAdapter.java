package com.marcos.estik.auth.adapter.out.password;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.marcos.estik.auth.application.port.out.PasswordEncodePort;

@Component 
public class BCryptPasswordAdapter implements PasswordEncodePort {

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public Boolean matches(String rawPassword, String encodedPassword) {
        return encoder.matches(rawPassword, encodedPassword);
    }

    @Override
    public String encode(String rawPassword) {
        return encoder.encode(rawPassword);
    }
}
