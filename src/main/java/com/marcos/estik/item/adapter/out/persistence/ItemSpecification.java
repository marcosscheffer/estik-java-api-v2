package com.marcos.estik.item.adapter.out.persistence;

import java.util.UUID;

import org.springframework.data.jpa.domain.Specification;

public final class ItemSpecification {
    
    public static Specification<ItemEntity> nameContain(String name) {
        return (name == null || name.isBlank())
            ? Specification.unrestricted()
            : (root, q, cb) -> cb.like(root.get("name"), "%" + name + "%");
    }

    public static Specification<ItemEntity> skuContain(String sku) {
        return (sku == null || sku.isBlank())
            ? Specification.unrestricted()
            : (root, q, cb) -> cb.like(root.get("code"), "%" + sku + "%");
    }

    public static Specification<ItemEntity> categoryIs(UUID categoryId) {
        return categoryId == null
            ? Specification.unrestricted()
            : (root, q, cb) -> cb.equal(root.get("categoryId"), categoryId);
    }
}
