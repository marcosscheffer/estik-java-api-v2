package com.marcos.estik.ticket.adapter.out.persistence;

import java.util.UUID;

import org.springframework.data.jpa.domain.Specification;

import com.marcos.estik.ticket.domain.enums.StatusEnum;

public final class TicketHistorySpecification {
    
    public static Specification<TicketHistoryEntity> statusIs(StatusEnum status) {
        return status == null 
            ? Specification.unrestricted()
            : (root, q, cb) -> cb.equal(root.get("status"), status);
    }

    public static Specification<TicketHistoryEntity> ticketIs(UUID ticketId) {
        return ticketId == null 
            ? Specification.unrestricted()
            : (root, q, cb) -> cb.equal(root.get("ticketId"), ticketId);
    }
}
