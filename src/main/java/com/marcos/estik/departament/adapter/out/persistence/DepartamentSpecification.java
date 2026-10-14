package com.marcos.estik.departament.adapter.out.persistence;

import java.util.UUID;

import org.springframework.data.jpa.domain.Specification;

public final class DepartamentSpecification {
    private DepartamentSpecification() {}

    public static Specification<DepartamentEntity> facilityIs(UUID facilityId) {
        return facilityId == null
            ? Specification.unrestricted()
            : (root, q, cb) -> cb.equal(root.get("facilityId"), facilityId);
    }

    public static Specification<DepartamentEntity> nameContain(String name) {
        return (name == null || name.isBlank())
            ? Specification.unrestricted()
            : (root, q, cb) -> cb.like(root.get("name"), "%" + name + "%");
    }

    public static Specification<DepartamentEntity> codeContain(String code) {
        return (code == null || code.isBlank())
            ? Specification.unrestricted()
            : (root, q, cb) -> cb.like(root.get("code"), "%" + code + "%");
    }
}
