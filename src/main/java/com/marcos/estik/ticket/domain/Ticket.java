package com.marcos.estik.ticket.domain;

import java.time.Instant;
import java.util.UUID;

import com.marcos.estik.ticket.domain.enums.PriorityEnum;
import com.marcos.estik.ticket.domain.enums.StatusEnum;
import com.marcos.estik.ticket.domain.exception.InvalidTicketStatusException;
import com.marcos.estik.ticket.domain.exception.TicketOwnerShipException;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter 
@AllArgsConstructor 
public class Ticket {
    private UUID id;
    private String title;
    private String description;
    private UUID specializationId;
    private UUID chatId;
    private UUID requesterId;
    private UUID facilityId;
    private UUID regulatorId;
    private StatusEnum status;
    private PriorityEnum priority;
    private Long version;
    private Instant createdAt;
    private Instant updatedAt;

    public static Ticket create(
        String title, 
        String description,
        UUID specializationId,
        UUID requesterId,
        UUID facilityId,
        PriorityEnum priority

    ) {
        return new Ticket(
            UUID.randomUUID(),
            title, 
            description,
            specializationId,
            null,
            requesterId, 
            facilityId, 
            null,
            StatusEnum.OPEN, 
            priority,
            null,
            Instant.now(), 
            Instant.now()
        );
    }


    public void forward(PriorityEnum priority, UUID actorId) {
        if (this.status == StatusEnum.CANCELLED || this.status == StatusEnum.RESOLVED) {
            throw new InvalidTicketStatusException("Ticket status invalid");
        }
        this.priority = priority;
        this.regulatorId = actorId;
        this.status = StatusEnum.FORWARDED;
        this.updatedAt = Instant.now();
    }

    public void assign(UUID chatId) {
        if (this.status != StatusEnum.FORWARDED
            && this.status != StatusEnum.REFORWARDED
            && this.status != StatusEnum.PROGRESS
        ) {
            throw new InvalidTicketStatusException("Ticket not be assigneed");
        }

        this.chatId = chatId;
        this.status = StatusEnum.PROGRESS;
        this.updatedAt = Instant.now();
    }

    public void resolve(UUID actorId) {
        if (!this.status.equals(StatusEnum.PROGRESS)) {
            throw new InvalidTicketStatusException("You cannot resolve this ticket");
        }

        this.status = StatusEnum.RESOLVED;
        this.updatedAt = Instant.now();
    }

    public void reopen(UUID actorId) {
        if(!actorId.equals(this.requesterId)) {
            throw new TicketOwnerShipException("you not owner of ticket");
        }

        if(!this.status.equals(StatusEnum.RESOLVED)) {
            throw new InvalidTicketStatusException("You cannot reopen this ticket");
        }

        this.status = StatusEnum.PROGRESS;
        this.updatedAt = Instant.now();
    }

    public void cancel(UUID actorId) {
        if(this.status.equals(StatusEnum.RESOLVED) || this.status.equals(StatusEnum.CANCELLED)) {
            throw new InvalidTicketStatusException("You cannot cancel this ticket");
        }

        if(!actorId.equals(this.requesterId)) {
            throw new TicketOwnerShipException("you not owner of ticket");
        }

        this.status = StatusEnum.CANCELLED;
        this.updatedAt = Instant.now();
    }

    public void updateDetails(
        UUID actorId,
        String title,
        String description,
        UUID specializationId
    ) {
         if(!actorId.equals(this.requesterId)) {
            throw new TicketOwnerShipException("you not owner of ticket");
        }

        this.title = title;
        this.description = description;
        this.specializationId = specializationId;
        this.updatedAt = Instant.now();
    }

    public void reforward(PriorityEnum priority, UUID actorId) {
        if (this.status != StatusEnum.PROGRESS && this.status != StatusEnum.FORWARDED) {
            throw new InvalidTicketStatusException("Ticket status invalid");
        }

        this.priority = priority;
        this.regulatorId = actorId;
        this.status = StatusEnum.REFORWARDED;
        this.updatedAt = Instant.now();
    }
}
