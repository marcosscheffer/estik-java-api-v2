package com.marcos.estik.user.application.port.out;

import java.util.Optional;
import java.util.UUID;

import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.application.pagination.PageResult;
import com.marcos.estik.user.domain.User;

public interface UserRepositoryPort {
    Optional<User> findByUsername(String username);

    Optional<User> findById(UUID id);

    Boolean existsByUsername(String username);

    PageResult<User> SearchByUsername(String username, PageQuery pageQuery);

    User save(User user);

}
