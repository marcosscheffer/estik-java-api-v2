package com.marcos.estik.ticket.adapter.out.persistence;

import java.time.Instant;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity 
@Table(name = "ticket_messages")
@Getter 
@Setter 
public class TicketMessageEntity {
    @Id 
    private UUID id;
    @Column(name = "ticket_id")
    private UUID ticketId;
    @Column(name = "author_id")
    private UUID authorId;
    private String body;
    @CreationTimestamp 
    @Column(name = "created_at")
    private Instant createdAt;
}
