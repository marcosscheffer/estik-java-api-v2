package com.marcos.estik.ticket.adapter.out.persistence.tickethistory;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface JpaTicketHistoryRepository 
    extends JpaRepository<TicketHistoryEntity, UUID>, JpaSpecificationExecutor<TicketHistoryEntity> {
    
}
