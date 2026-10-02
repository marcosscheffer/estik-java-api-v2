package com.marcos.estik.user.application.port.out;

import java.util.Optional;
import java.util.UUID;

import com.marcos.estik.user.domain.User;

public interface UserRepositoryPort {
    Optional<User> findByUsername(String username);

    Optional<User> findById(UUID id);

    Boolean ExistsByUsername(String username);

    User save(User user);

}
