package com.marcos.estik.stock.adapter.out.persistence;

import java.util.UUID;

import org.springframework.data.jpa.domain.Specification;

public final class StockBalanceSpecification {
    
    public static Specification<StockBalanceEntity> faciltiyIs(UUID facilityId) {
        return facilityId == null
            ? Specification.unrestricted()
            : (root, q, cb) -> cb.equal(root.get("facilityId"), facilityId);
    }

    public static Specification<StockBalanceEntity> departamentIs(UUID departamentId) {
        return departamentId == null
            ?  Specification.unrestricted()
            : (root, q, cb) -> cb.equal(root.get("departamentId"), departamentId);
    }

    public static Specification<StockBalanceEntity> itemIs(UUID itemId) {
        return itemId == null
            ? Specification.unrestricted()
            : (root, q, cb) -> cb.equal(root.get("itemId"), itemId);
    }
}
