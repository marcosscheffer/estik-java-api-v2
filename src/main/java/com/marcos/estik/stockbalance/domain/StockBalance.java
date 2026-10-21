package com.marcos.estik.stockbalance.domain;

import java.time.Instant;
import java.util.UUID;

import com.marcos.estik.stockbalance.domain.exception.QuantityMovementInvalidException;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor 
public class StockBalance {
    private UUID id;
    private UUID facilityId;
    private UUID departamentId;
    private UUID itemId;
    private Long quantity;
    private Instant updatedAt;

    public static StockBalance create(
        UUID facilityId,
        UUID departamentId,
        UUID itemId,
        Long quantity
    ) {
        return new StockBalance(
            UUID.randomUUID(), 
            facilityId, 
            departamentId, 
            itemId, 
            quantity, 
            Instant.now()
        );
    }

    public void adjust(
        Long quantity
    ) {
        if(quantity <= 0) {
            throw new QuantityMovementInvalidException("quantity should never be less than 0");
        }

        this.quantity = quantity;
        this.updatedAt = Instant.now();
    }

    public void exit(
        Long quantity
    ) {
        if(this.quantity < quantity) {
            throw new QuantityMovementInvalidException("quantity should never be less than 0");
        }
        this.quantity -= quantity;
        this.updatedAt = Instant.now();
    }

    public void entry(
        Long quantity
    ) {
        this.quantity += quantity;
        this.updatedAt = Instant.now();
    }
}
