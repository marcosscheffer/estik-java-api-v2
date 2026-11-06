package com.marcos.estik.access.adapter.out.persistence.permission;

import java.time.Instant;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.marcos.estik.shared.domain.user.PermissionEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity 
@Table(name = "permissions")
@Getter 
@Setter 
public class PermissionEntity {
    @Id 
    private UUID id;

    @Column(name = "role_id")
    private UUID roleId;

    @Enumerated(EnumType.STRING)
    private PermissionEnum permission;

    @CreationTimestamp 
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @UpdateTimestamp 
    @Column(name = "updated_at", nullable = false, updatable = true)
    private Instant updatedAt;
}
