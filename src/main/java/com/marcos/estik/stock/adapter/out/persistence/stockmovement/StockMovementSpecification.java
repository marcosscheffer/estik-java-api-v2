package com.marcos.estik.stock.adapter.out.persistence.stockmovement;

import java.util.UUID;

import org.springframework.data.jpa.domain.Specification;

import com.marcos.estik.stock.domain.TypeMovementEnum;

public final class StockMovementSpecification {
    public static Specification<StockMovementEntity> departamentIs(UUID departamentId) {
        return departamentId == null
            ? Specification.unrestricted()
            : (root, q, cb) -> cb.or(
                cb.equal(
                    root.get("departamentSource"), departamentId), 
                    cb.equal(root.get("departamentDestination"), departamentId
                )
            );
    }

    public static Specification<StockMovementEntity> facilityIs(UUID facilityId) {
        return facilityId == null
            ? Specification.unrestricted()
            : (root, q, cb) -> cb.or(
                cb.equal(
                    root.get("facilitySource"), facilityId), 
                    cb.equal(root.get("facilityDestination"), facilityId
                )
            );
    }

    public static Specification<StockMovementEntity> typeIs(TypeMovementEnum type) {
        return type == null
            ? Specification.unrestricted()
            : (root, q, cb) -> cb.equal(root.get("type"), type);
    }
}
