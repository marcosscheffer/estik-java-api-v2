package com.marcos.estik.solution.adapter.out.persistence.solutionpost;

import java.util.UUID;

import org.springframework.data.jpa.domain.Specification;

public final class SolutionPostSpecification {
    
    public static Specification<SolutionPostEntity> titleContain(String title) {
        return title.isEmpty() || title == null 
            ? Specification.unrestricted()
            : (root, q, cb) -> cb.like(root.get("title"), "%" + title + "%");
    }

    public static Specification<SolutionPostEntity> specializationIs(UUID specializationId) {
        return specializationId == null
            ? Specification.unrestricted()
            : (root, q, cb) -> cb.equal(root.get("specializationId"), specializationId);
    }

}
