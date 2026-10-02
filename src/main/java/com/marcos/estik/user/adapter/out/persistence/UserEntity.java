package com.marcos.estik.user.adapter.out.persistence;

import java.time.Instant;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity 
@Table(name = "users")
@Getter 
@Setter 
public class UserEntity {
    @Id 
    private UUID id;

    private String username;
    private String email;
    private String password;
    private String role;
    private Boolean active;

    @CreationTimestamp 
    @Column (
        updatable = false,
        nullable = false
    )
    private Instant createdAt;
    
    @UpdateTimestamp 
    @Column (
        nullable = false
    )
    private Instant updatedAt;

    
}
