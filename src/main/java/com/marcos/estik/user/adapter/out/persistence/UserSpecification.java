package com.marcos.estik.user.adapter.out.persistence;

import java.util.UUID;

import org.springframework.data.jpa.domain.Specification;

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

    public static Specification<UserEntity> roleIs(UUID role) {
        return role == null
            ? Specification.unrestricted()
            : (root, q, cb) -> cb.equal(root.get("roleId"), role);
    }

    public static Specification<UserEntity> specializationIs(UUID specializationId) {
        return specializationId == null 
            ? Specification.unrestricted()
            : (root, q, cb) -> cb.equal(root.get("specializationId"), specializationId);
    }
}
