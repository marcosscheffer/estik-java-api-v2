package com.marcos.estik.specialization.adapter.out.persistence;

import org.springframework.data.jpa.domain.Specification;

public final class SpecializationSpecification {

    public static Specification<SpecializationEntity> nameContain(String name) {
        return (name.isBlank() || name == null)
            ? Specification.unrestricted()
            : (root, q, cb) -> cb.like(root.get("name"), "%" + name + "%");
    }
}
