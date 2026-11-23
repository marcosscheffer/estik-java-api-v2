package com.marcos.estik.supplier.adapter.out.persistence;

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
@Table(name = "suppliers")
@Getter 
@Setter 
public class SupplierEntity {
    @Id 
    private UUID id;
    private String name;
    
    @CreationTimestamp 
    @Column(name = "created_at")
    private Instant createdAt;
}
