package com.marcos.estik.departament.adapter.out.persistence;

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
@Table(name = "departaments")
@Getter
@Setter 
public class DepartamentEntity {
    @Id 
    private UUID id;

    private String name;
    private String code;
    private UUID facilityId;

    @CreationTimestamp 
    @Column(name = "created_at")
    private Instant createdAt;
    
    @UpdateTimestamp 
    @Column(name = "updated_at")
    private Instant updatedAt;
}
