package com.marcos.estik.ticket.adapter.out.persistence;

import java.time.Instant;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.marcos.estik.ticket.domain.enums.PriorityEnum;
import com.marcos.estik.ticket.domain.enums.StatusEnum;
import com.marcos.estik.ticket.domain.enums.TicketTypeEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;

@Entity 
@Table(name = "tickets")
@Getter 
@Setter 
public class TicketEntity {
    @Id 
    private UUID id;

    private String title;
    private String description;

    @Column(name = "requester_id")
    private UUID requesterId;
    @Column(name = "facility_id")
    private UUID facilityId;
    @Column(name = "assignee_id")
    private UUID assigneeId;
    @Column(name = "regulator_id")
    private UUID regulatorId;
    @Column(name = "forwarded_id")
    private UUID forwardedId;

    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    @Enumerated(EnumType.STRING)
    private PriorityEnum priority;
    
    @Enumerated(EnumType.STRING)
    private TicketTypeEnum ticketType;

    @Version 
    private Long version;

    @CreationTimestamp 
    @Column(name = "created_at")
    private Instant createdAt;
    @UpdateTimestamp 
    @Column(name = "updated_at")
    private Instant updatedAt;
}
