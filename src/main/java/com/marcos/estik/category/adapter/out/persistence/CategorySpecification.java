package com.marcos.estik.category.adapter.out.persistence;

import org.springframework.data.jpa.domain.Specification;

public final class CategorySpecification {
    public static Specification<CategoryEntity> nameContain(String name) {
        return (name == null || name.isBlank())
            ? Specification.unrestricted()
            : (root, q, cb) -> cb.like(root.get("name"), "%" + name + "%");
    }
}
