package com.marcos.estik.stock.adapter.out.persistence;

import java.util.UUID;

import org.springframework.data.jpa.domain.Specification;

import com.marcos.estik.stock.domain.TypeMovementEnum;

public final class StockMovementSpecification {
    public static Specification<StockMovementEntity> departamentIs(UUID departamentId) {
        return departamentId == null
            ? Specification.unrestricted()
            : (root, q, cb) -> cb.or(
                cb.equal(
                    root.get("departamentSourceId"), departamentId), 
                    cb.equal(root.get("departamentDestinationId"), departamentId
                )
            );
    }

    public static Specification<StockMovementEntity> facilityIs(UUID facilityId) {
        return facilityId == null
            ? Specification.unrestricted()
            : (root, q, cb) -> cb.or(
                cb.equal(
                    root.get("facilitySourceId"), facilityId), 
                    cb.equal(root.get("facilityDestinationId"), facilityId
                )
            );
    }

    public static Specification<StockMovementEntity> typeIs(TypeMovementEnum type) {
        return type == null
            ? Specification.unrestricted()
            : (root, q, cb) -> cb.equal(root.get("type"), type);
    }
}
