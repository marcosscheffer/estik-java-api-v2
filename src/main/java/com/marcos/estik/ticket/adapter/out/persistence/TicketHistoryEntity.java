package com.marcos.estik.ticket.adapter.out.persistence;

import java.time.Instant;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import com.marcos.estik.ticket.domain.StatusEnum;
import com.marcos.estik.ticket.domain.TicketTypeEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity 
@Table(name = "ticket_histories")
@Getter 
@Setter 
public class TicketHistoryEntity {
    @Id 
    private UUID id;

    @Column(name = "ticket_id")
    private UUID ticketId;
    
    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    @Enumerated(EnumType.STRING)
    private TicketTypeEnum type;
    
    @CreationTimestamp 
    @Column(name = "created_at")
    private Instant createdAt;
}
