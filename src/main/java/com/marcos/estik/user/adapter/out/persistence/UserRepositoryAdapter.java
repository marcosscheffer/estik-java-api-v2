package com.marcos.estik.user.adapter.out.persistence;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.marcos.estik.user.application.port.out.UserRepositoryPort;
import com.marcos.estik.user.domain.User;

import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class UserRepositoryAdapter implements UserRepositoryPort {

    private final JpaUserRepository jpaUserRepository;
    private final UserPersistenceMapper userPersistenceMapper;

    @Override
    public Optional<User> findByUsername(String username) {
        return jpaUserRepository.findByUsername(username)
            .map(userPersistenceMapper::toDomain);
    }

    @Override
    public Optional<User> findById(UUID id) {
        return jpaUserRepository.findById(id)
            .map(userPersistenceMapper::toDomain);
    }
    
}
