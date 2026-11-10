package com.marcos.estik.user.domain;

import java.time.Instant;
import java.util.UUID;

import com.marcos.estik.user.domain.exception.CannotAssignSelfException;
import com.marcos.estik.user.domain.exception.CannotDeactiveSelfException;
import com.marcos.estik.user.domain.exception.UserOwnershipException;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor 
public class User {
    private UUID id;
    private String username;
    private String email;
    private UUID specializationId;
    private String password;
    private Instant createdAt;
    private Instant updatedAt;
    private UUID roleId;
    private Boolean active;
    private UUID facilityId;

    public static User create(String username, String email, String password, UUID facility, UUID specializationId) {
        return new User(
            UUID.randomUUID(),
            username,
            email,
            specializationId,
            password,
            Instant.now(),
            Instant.now(),
            null,
            Boolean.FALSE,
            facility
        );
    }

    public void updateInfo(
        UUID actorId,
        String username,
        String email
    ) {
        if (!this.id.equals(actorId)) {
            throw new UserOwnershipException("Operation not permitted");
        }
        
        this.username = username;
        this.email = email;
        this.updatedAt = Instant.now();
    }

    public void activate() {
        this.active = true;
        this.updatedAt = Instant.now();
    }

    public void deactivate(UUID actorId) {
        if (this.id.equals(actorId)) {
            throw new CannotDeactiveSelfException("not possible to deactivate yourself");
        }

        this.active = false;
        this.updatedAt = Instant.now();
    }

    public void moveTo(UUID facilityId) {
        this.facilityId = facilityId;
        this.updatedAt = Instant.now();
    }

    public void changeRole(UUID actorId, UUID role) {
        if (this.id.equals(actorId)) {
            throw new CannotAssignSelfException("cannot assign to self");
        }

        this.roleId = role;
        this.updatedAt = Instant.now();
    }

    public void changeSpecialization(UUID specializationId) {
        this.specializationId = specializationId;
        this.updatedAt = Instant.now();
    }
}
