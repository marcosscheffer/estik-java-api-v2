package com.marcos.estik.stock.adapter.out.persistence.stockbalance;

import java.time.Instant;
import java.util.UUID;

import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity 
@Table(name = "stock_balance")
@Getter 
@Setter 
public class StockBalanceEntity {
    @Id 
    private UUID id;

    @Column(name = "facility_id")
    private UUID facilityId;

    @Column(name = "departament_id")
    private UUID departamentId;

    @Column(name = "item_id")
    private UUID itemId;
    private Long quantity;
    
    @UpdateTimestamp 
    @Column(name = "updated_at")
    private Instant updatedAt;
}
