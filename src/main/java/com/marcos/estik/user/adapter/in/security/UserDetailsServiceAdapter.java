package com.marcos.estik.user.adapter.in.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.marcos.estik.user.adapter.out.persistence.UserRepositoryAdapter;

import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class UserDetailsServiceAdapter implements UserDetailsService {

    private final UserRepositoryAdapter userRepositoryAdapter;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepositoryAdapter.findByUsername(username)
            .map(UserAuthenticated::new)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
    
}
