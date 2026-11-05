package com.marcos.estik.computer.adapter.out.persistence.computerhistory;

import java.util.UUID;

import org.springframework.data.jpa.domain.Specification;

import com.marcos.estik.computer.domain.DirectionEnum;

public final class ComputerHistorySpecification {
    private ComputerHistorySpecification() {}

    public static Specification<ComputerHistoryEntity> directionIs(DirectionEnum direction) {
        return direction == null
            ? Specification.unrestricted()
            : (root, q, cb) -> cb.equal(root.get("direction"), direction);
    }

    public static Specification<ComputerHistoryEntity> computerIs(UUID computerId) {
        return computerId == null 
            ? Specification.unrestricted()
            : (root, q, cb) -> cb.equal(root.get("computerId"), computerId);
    }

    public static Specification<ComputerHistoryEntity> departamentIs(UUID departamentId) {
        return departamentId == null
            ? Specification.unrestricted()
            : (root, q, cb) -> cb.equal(root.get("departamentId"), departamentId);
    }
}
