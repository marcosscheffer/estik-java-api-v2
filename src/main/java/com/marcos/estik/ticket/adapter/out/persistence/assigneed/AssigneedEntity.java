package com.marcos.estik.ticket.adapter.out.persistence.assigneed;

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
@Table(name = "assigneeds")
@Getter 
@Setter 
public class AssigneedEntity {
    @Id
    UUID id;
    
    @Column(name = "user_id")
    UUID userId;

    @Column(name = "ticket_id")
    UUID ticketId;
    
    @CreationTimestamp
    @Column(name = "created_at")
    Instant createdAt;
}
