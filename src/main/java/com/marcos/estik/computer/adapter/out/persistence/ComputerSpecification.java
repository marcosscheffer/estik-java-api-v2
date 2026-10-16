package com.marcos.estik.computer.adapter.out.persistence;

import java.util.UUID;

import org.springframework.data.jpa.domain.Specification;

public final class ComputerSpecification {
    private ComputerSpecification() {}

    public static Specification<ComputerEntity> facilityIs(UUID facilityId) {
        return facilityId == null 
            ? Specification.unrestricted()
            : (root, q, cb) -> cb.equal(root.get("facility_id"), facilityId);
    }

    public static Specification<ComputerEntity> departamentIs(UUID departamentId) {
        return departamentId == null 
            ? Specification.unrestricted()
            : (root, q, cb) -> cb.equal(root.get("departament_id"), departamentId);
    }

    public static Specification<ComputerEntity> nameContain(String name) {
        return (name == null || name.isBlank())
            ? Specification.unrestricted()
            : (root, q, cb) -> cb.like(root.get("name"), "%" + name + "%");
    }
}
