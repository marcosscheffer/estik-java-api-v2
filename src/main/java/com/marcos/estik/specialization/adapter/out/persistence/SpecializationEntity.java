package com.marcos.estik.specialization.adapter.out.persistence;

import java.time.Instant;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity 
@Table(name = "specializations")
@Getter
@Setter 
public class SpecializationEntity {
    @Id 
    private UUID id;
    private String name;

    @CreationTimestamp 
    @Column(name = "created_at")
    private Instant createdAt;
}
