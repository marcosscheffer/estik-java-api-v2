package com.marcos.estik.user.application.port.out;

import java.util.UUID;

import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.application.pagination.PageResult;
import com.marcos.estik.user.domain.User;

public interface UserRepositoryPort {
    User findByUsername(String username);

    PageResult<User> SearchUsers(UUID facilityId, String username, PageQuery page);

    User findById(UUID id);

    Boolean existsByUsername(String username);

    Boolean existsById(UUID id);

    User save(User user);

}
