package com.marcos.estik.ticket.adapter.out.persistence;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.marcos.estik.ticket.domain.PriorityEnum;
import com.marcos.estik.ticket.domain.StatusEnum;

public interface JpaTicketRepository extends JpaRepository<TicketEntity, UUID> {

    Page<TicketEntity> findByPriorityAndTitleContainingIgnoreCase(PriorityEnum priority, String title, Pageable pageable);

    Page<TicketEntity> findByStatusAndTitleContainingIgnoreCase(StatusEnum status, String title, Pageable pageable);

    Page<TicketEntity> findByTitleContainingIgnoreCase(String title, Pageable pageable);

    Page<TicketEntity> findByFacilityId(UUID userId, Pageable pageable);

    Page<TicketEntity> findByRequesterId(UUID requesterId, Pageable pageable);

    Page<TicketEntity> findByAssigneeId(UUID assigneeId, Pageable pageable);
    
}
