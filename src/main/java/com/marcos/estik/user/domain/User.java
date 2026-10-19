package com.marcos.estik.user.domain;

import java.time.Instant;
import java.util.UUID;

import com.marcos.estik.shared.domain.exception.DomainCannotMovedException;
import com.marcos.estik.shared.domain.user.RoleEnum;
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
    private String password;
    private RoleEnum role;
    private Instant createdAt;
    private Instant updatedAt;
    private Boolean active;
    private UUID facilityId;

    public static User create(String username, String email, String password, UUID facility) {
        return new User(
            UUID.randomUUID(),
            username,
            email,
            password,
            RoleEnum.USER,
            Instant.now(),
            Instant.now(),
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
        if(this.facilityId.equals(facilityId)) {
            throw new DomainCannotMovedException("failed to move");
        }
        this.facilityId = facilityId;
        this.updatedAt = Instant.now();
    }

    public void changeRole(UUID actorId, RoleEnum role) {
        if (this.id.equals(actorId)) {
            throw new CannotAssignSelfException("cannot assign to self");
        }

        this.role = role;
        this.updatedAt = Instant.now();
    }
}
