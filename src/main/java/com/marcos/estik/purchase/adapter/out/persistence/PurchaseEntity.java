package com.marcos.estik.purchase.adapter.out.persistence;

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
@Table(name = "purchases")
@Getter 
@Setter 
public class PurchaseEntity {
    @Id 
    private UUID id;
    
    @Column(name = "file_id")
    private UUID fileId;

    @Column(name = "supplier_id")
    private UUID supplierId;
    private String code;

    @CreationTimestamp 
    @Column(name = "created_at")
    private Instant createdAt;   
}
