package com.marcos.estik.user.adapter.out.persistence;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.application.pagination.PageResult;
import com.marcos.estik.shared.domain.user.RoleEnum;
import com.marcos.estik.user.application.port.out.UserRepositoryPort;
import com.marcos.estik.user.domain.User;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class UserPersistenceRepositoryAdapter implements UserRepositoryPort {

    private final JpaUserRepository jpaUserRepository;
    private final UserPersistenceMapper userPersistenceMapper;

    @Override
    public User findByUsername(String username) {
        return jpaUserRepository.findByUsername(username)
            .map(userPersistenceMapper::toDomain)
            .orElseThrow(() -> new EntityNotFoundException("Username not found"));
    }

    @Override
    public User findById(UUID id) {
        return jpaUserRepository.findById(id)
            .map(userPersistenceMapper::toDomain)
            .orElseThrow(() -> new EntityNotFoundException("Id not found"));
    }

    @Override
    public User save(User user) {
        UserEntity userEntity = userPersistenceMapper.toEntity(user);
        UserEntity userSaved = jpaUserRepository.save(userEntity);
        return userPersistenceMapper.toDomain(userSaved);
    }

    @Override
    public Boolean existsByUsername(String username) {
       return jpaUserRepository.existsByUsername(username);
    }


    @Override 
    public PageResult<User> SearchUsers(UUID facilityId, String username, PageQuery page) {
        Specification<UserEntity> spec = Specification.allOf(
            UserSpecification.facilityIs(facilityId),
            UserSpecification.usernameContains(username)
        );
        Page<UserEntity> users = jpaUserRepository.findAll(spec, PageRequest.of(page.page(), page.size()));

        return new PageResult<User>(
            users.getContent().stream().map(userPersistenceMapper::toDomain).toList(), 
            users.getNumber(), 
            users.getSize(), 
            users.getTotalElements(), 
            users.getTotalPages()
        );
    }

    @Override
    public Boolean existsById(UUID id) {
        return jpaUserRepository.existsById(id);
    }

    @Override
    public List<User> findByRole(RoleEnum role) {
        return jpaUserRepository.findByRole(role);
    }
}
