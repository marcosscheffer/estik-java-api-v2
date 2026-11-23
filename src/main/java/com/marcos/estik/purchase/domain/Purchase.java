package com.marcos.estik.purchase.domain;

import java.time.Instant;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter 
@AllArgsConstructor 
public class Purchase {
    
    private UUID id;
    private UUID fileId;
    private UUID supplierId;
    private String code;
    private Instant createdAt;

    public static Purchase create(
        UUID fileId,
        UUID supplierId,
        String code
    ) {
        return new Purchase(
            UUID.randomUUID(), 
            fileId, 
            supplierId,
            code, 
            Instant.now()
        );
    }
}
