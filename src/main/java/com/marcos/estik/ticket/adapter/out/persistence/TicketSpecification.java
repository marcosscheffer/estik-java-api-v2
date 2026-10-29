package com.marcos.estik.ticket.adapter.out.persistence;

import java.util.UUID;

import org.springframework.data.jpa.domain.Specification;

import com.marcos.estik.ticket.domain.enums.PriorityEnum;
import com.marcos.estik.ticket.domain.enums.StatusEnum;
import com.marcos.estik.ticket.domain.enums.TicketTypeEnum;

public final class TicketSpecification {

    private TicketSpecification() {}

    public static Specification<TicketEntity> facilityIs(UUID facilityId) {
        return facilityId == null 
            ? Specification.unrestricted() 
            : (root, q, cb) -> cb.equal(root.get("facilityId"), facilityId);
    }

    public static Specification<TicketEntity> statusIs(StatusEnum status) {
        return status == null 
            ? Specification.unrestricted() 
            : (root, q, cb) -> cb.equal(root.get("status"), status);
    }

    public static Specification<TicketEntity> typeIs(TicketTypeEnum type) {
        return type == null
            ? Specification.unrestricted()
            : (root, q, cb) -> cb.equal(root.get("ticketType"), type);
    }

    public static Specification<TicketEntity> priorityIs(PriorityEnum priority) {
        return priority == null 
            ? Specification.unrestricted() 
            : (root, q, cb) -> cb.equal(root.get("priority"), priority);
    }

    public static Specification<TicketEntity> requesterIs(UUID requesterId) {
        return requesterId == null 
            ? Specification.unrestricted() 
            : (root, q, cb) -> cb.equal(root.get("requesterId"), requesterId);
    }

    public static Specification<TicketEntity> forwardedIs(UUID forwardedId) {
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
}
