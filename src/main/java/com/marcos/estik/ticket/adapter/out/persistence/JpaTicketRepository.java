package com.marcos.estik.ticket.adapter.out.persistence;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import com.marcos.estik.ticket.domain.PriorityEnum;
import com.marcos.estik.ticket.domain.StatusEnum;
import com.marcos.estik.ticket.domain.Ticket;

public interface JpaTicketRepository extends JpaRepository<TicketEntity, UUID> {

    Page<Ticket> findByPriorityAndTitleContainingIgnoreCase(PriorityEnum priority, String title, PageRequest of);

    Page<Ticket> findByStatusAndTitleContainingIgnoreCase(StatusEnum status, String title, PageRequest of);

    Page<Ticket> findByTitleContainingIgnoreCase(String title, PageRequest of);
    
}
