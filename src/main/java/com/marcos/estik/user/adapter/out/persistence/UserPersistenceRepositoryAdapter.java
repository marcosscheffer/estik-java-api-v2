package com.marcos.estik.user.adapter.out.persistence;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.application.pagination.PageResult;
import com.marcos.estik.user.application.port.out.UserRepositoryPort;
import com.marcos.estik.user.domain.User;

import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class UserPersistenceRepositoryAdapter implements UserRepositoryPort {

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

    @Override
    public User save(User user) {
        UserEntity userEntity = userPersistenceMapper.toEntity(user);
        jpaUserRepository.save(userEntity);
        return userPersistenceMapper.toDomain(userEntity);
    }

    @Override
    public Boolean existsByUsername(String username) {
       return jpaUserRepository.existsByUsername(username);
    }

    @Override
    public PageResult<User> SearchByUsername(String username, PageQuery pageQuery) {
        Page<UserEntity> page = jpaUserRepository.findByUsernameContainingIgnoreCase(
            username,
            PageRequest.of(
                pageQuery.page(),
                pageQuery.size(),
                Sort.by("username")
            )
        );

        return new PageResult<>(
            page.getContent().stream().map(userPersistenceMapper::toDomain).toList(),
            page.getNumber(), page.getSize(), page.getTotalElements(), page.getTotalPages()
        );
    }
}
