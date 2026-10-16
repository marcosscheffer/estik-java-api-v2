package com.marcos.estik.computer.adapter.out.persistence;

import java.time.Instant;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.marcos.estik.computer.domain.OsEnum;
import com.marcos.estik.computer.domain.StorageTypeEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity 
@Table(name = "computers")
@Getter 
@Setter 
public class ComputerEntity {
    @Id 
    private UUID id;
    private String name;
    private String cpu;
    private String ram;
    
    @Enumerated(EnumType.STRING)
    private OsEnum os;

    @Column(name = "storage_type")
    @Enumerated(EnumType.STRING)
    private StorageTypeEnum storageType;

    private String storage;

    @Column(name = "facility_id")
    private UUID facilityId;

    @Column(name = "departament_id")
    private UUID departamentId;

    @CreationTimestamp 
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @UpdateTimestamp 
    @Column(name = "updated_at", updatable = true)
    private Instant updatedAt;
    
}
