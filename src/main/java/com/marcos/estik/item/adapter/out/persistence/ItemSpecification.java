package com.marcos.estik.item.adapter.out.persistence;

import org.springframework.data.jpa.domain.Specification;

public final class ItemSpecification {
    
    public static Specification<ItemEntity> nameContain(String name) {
        return (name == null || name.isBlank())
            ? Specification.unrestricted()
            : (root, q, cb) -> cb.like(root.get("name"), "%" + name + "%");
    }

    public static Specification<ItemEntity> codeContaim(String code) {
        return (code == null || code.isBlank())
            ? Specification.unrestricted()
            : (root, q, cb) -> cb.like(root.get("code"), "%" + code + "%");
    }
}
