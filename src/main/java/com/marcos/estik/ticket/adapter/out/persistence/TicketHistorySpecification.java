package com.marcos.estik.ticket.adapter.out.persistence;

import org.springframework.data.jpa.domain.Specification;

import com.marcos.estik.ticket.domain.StatusEnum;
import com.marcos.estik.ticket.domain.TicketTypeEnum;

public final class TicketHistorySpecification {
    
    public static Specification<TicketHistoryEntity> statusIs(StatusEnum status) {
        return status == null 
            ? Specification.unrestricted()
            : (root, q, cb) -> cb.equal(root.get("status"), status);
    }

    public static Specification<TicketHistoryEntity> typeIs(TicketTypeEnum type) {
        return type == null 
            ? Specification.unrestricted()
            : (root, q, cb) -> cb.equal(root.get("type"), type);
    }
}
