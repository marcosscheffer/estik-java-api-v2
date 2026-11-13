package com.marcos.estik.ticket.adapter.out.persistence.ticket;

import java.util.UUID;

import org.springframework.data.jpa.domain.Specification;

import com.marcos.estik.ticket.domain.enums.PriorityEnum;
import com.marcos.estik.ticket.domain.enums.StatusEnum;

import jakarta.persistence.criteria.Predicate;

public final class TicketSpecification {

    private TicketSpecification() {}

    public static Specification<TicketEntity> facilityIdIs(UUID facilityId) {
        return facilityId == null 
            ? Specification.unrestricted() 
            : (root, q, cb) -> cb.equal(root.get("facilityId"), facilityId);
    }

    public static Specification<TicketEntity> statusIs(StatusEnum status) {
        return status == null 
            ? Specification.unrestricted() 
            : (root, q, cb) -> cb.equal(root.get("status"), status);
    }

    public static Specification<TicketEntity> specializationIs(UUID type) {
        return type == null
            ? Specification.unrestricted()
            : (root, q, cb) -> cb.equal(root.get("specializationId"), type);
    }

    public static Specification<TicketEntity> priorityIs(PriorityEnum priority) {
        return priority == null 
            ? Specification.unrestricted() 
            : (root, q, cb) -> cb.equal(root.get("priority"), priority);
    }

    public static Specification<TicketEntity> requesterIdIs(UUID requesterId) {
        return requesterId == null 
            ? Specification.unrestricted() 
            : (root, q, cb) -> cb.equal(root.get("requesterId"), requesterId);
    }

    public static Specification<TicketEntity> forwardedIdIs(UUID forwardedId) {
        return forwardedId == null
            ? Specification.unrestricted()
            : (root, q, cb) -> cb.equal(root.get("forwardedId"), forwardedId);
    }

    public static Specification<TicketEntity> assigneeIs(UUID assigneeId) {
        return assigneeId == null 
            ? Specification.unrestricted() 
            : (root, q, cb) -> cb.equal(root.get("assigneeId"), assigneeId);
    }

    public static Specification<TicketEntity> regulatorIs(UUID regulatorId) {
        return regulatorId == null
            ? Specification.unrestricted()
            : (root, q, cb) -> cb.equal(root.get("regulatorId"), regulatorId);
    }

    public static Specification<TicketEntity> titleContains(String title) {
        return (title == null || title.isBlank()) 
            ? Specification.unrestricted() 
            : (root, q, cb) -> cb.like(cb.lower(root.get("title")), "%" + title.toLowerCase() + "%");
    }

    public static Specification<TicketEntity> FilterIs(UUID userId, UUID specializationId) {
        if (userId == null) {
            return Specification.unrestricted();
        }

        return (root, q, cb) -> {
            Predicate involved = cb.or(
                cb.equal(root.get("requesterId"), userId),
                cb.equal(root.get("forwardedId"), userId),
                cb.equal(root.get("assigneeId"), userId)
            );

            if (specializationId == null) {
                return involved;
            }

            return cb.or(
                involved,
                cb.equal(root.get("specializationId"), specializationId)
            );
        };
    }
}
