package com.marcos.estik.facility.adapter.out.persistence;

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
@Table(name = "facilities")
@Getter 
@Setter 
public class FacilityEntity {
    @Id
    private UUID id;

    String name;
    String code;
    
    @UpdateTimestamp
    @Column(
        name = "updated_at",
        updatable = true,
        nullable = false
    )
    Instant updatedAt;
    
    @CreationTimestamp 
    @Column(
        name = "created_at",
        updatable = false,
        nullable = false
    )
    Instant createdAt;

}
