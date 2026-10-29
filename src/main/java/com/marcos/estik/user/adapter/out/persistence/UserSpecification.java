package com.marcos.estik.user.adapter.out.persistence;

import java.util.UUID;

import org.springframework.data.jpa.domain.Specification;

import com.marcos.estik.shared.domain.user.FunctionEnum;
import com.marcos.estik.shared.domain.user.RoleEnum;

public final class UserSpecification {
    private UserSpecification() {}

    public static Specification<UserEntity> facilityIs(UUID facilityId) {
        return facilityId == null 
            ? Specification.unrestricted()
            : (root, q, cb) -> cb.equal(root.get("facilityId"), facilityId);
    }

    public static Specification<UserEntity> usernameContains(String username) {
        return (username == null || username.isBlank()) 
            ? Specification.unrestricted()
            : (root, q, cb) -> cb.like(cb.lower(root.get("username")), "%" + username.toLowerCase() + "%");
    }

    public static Specification<UserEntity> roleIs(RoleEnum role) {
        return role == null
            ? Specification.unrestricted()
            : (root, q, cb) -> cb.equal(root.get("role"), role);
    }

    public static Specification<UserEntity> functionIs(FunctionEnum function) {
        return function == null
            ? Specification.unrestricted()
            : (root, q, cb) -> cb.equal(root.get("function"), function);
    }

}
