package com.marcos.estik.stock.adapter.out.persistence;

import java.time.Instant;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import com.marcos.estik.stock.domain.TypeMovementEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity 
@Table(name = "stock_movement")
@Getter 
@Setter 
public class StockMovementEntity {
    @Id 
    private UUID id;

    @Column(name = "item_id")
    private UUID itemId;
    
    @Column(name = "facility_source_id")
    private UUID facilitySourceId;

    @Column(name = "facility_destination_id")
    private UUID facilityDestinationId;

    @Column(name = "departament_source_id")
    private UUID departamentSourceId;

    @Column(name = "departament_destination_id")
    private UUID departamentDestinationId;
    private Long quantity;
    private String description;

    @Enumerated(EnumType.STRING)
    private TypeMovementEnum type;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;
}