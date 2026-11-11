package com.marcos.estik.ticket.domain;

import java.time.Instant;
import java.util.UUID;

import com.marcos.estik.ticket.domain.enums.PriorityEnum;
import com.marcos.estik.ticket.domain.enums.StatusEnum;
import com.marcos.estik.ticket.domain.exception.InvalidTicketStatusException;
import com.marcos.estik.ticket.domain.exception.TicketAlreadyAssignedException;
import com.marcos.estik.ticket.domain.exception.TicketNotAssignableException;
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
    private UUID assigneeId;
    private UUID regulatorId;
    private UUID forwardedId;
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
            null,
            null,
            StatusEnum.OPEN, 
            priority,
            null,
            Instant.now(), 
            Instant.now()
        );
    }

    public void forward(UUID actorId, UUID userId, PriorityEnum priority) {
        if (!this.status.equals(StatusEnum.OPEN) && !this.status.equals(StatusEnum.FORWARDED)) {
            throw new InvalidTicketStatusException("Ticket status invalid for this operation");
        }

        if (this.requesterId.equals(userId)) {
            throw new TicketOwnerShipException("Ticket not forwaded for you");
        }

        this.priority = priority;
        this.regulatorId = actorId;
        this.forwardedId = userId;
        this.assigneeId = null;
        this.status = StatusEnum.FORWARDED;
        this.updatedAt = Instant.now();
    }

    public void assignTo(UUID userId, UUID chatId) {
        if (this.status.equals(StatusEnum.CANCELLED) || this.status.equals(StatusEnum.RESOLVED)) {
            throw new TicketNotAssignableException(id);
        }

        if(this.requesterId.equals(userId)) {
            throw new TicketOwnerShipException("Ticket not be assumed");
        }

        if (this.assigneeId != null) {
            throw new TicketAlreadyAssignedException(id);
        }

        if (this.forwardedId == null) {
            throw new InvalidTicketStatusException("Ticket not forwaded");
        }

        if (!this.forwardedId.equals(userId)) {
            throw new TicketOwnerShipException("Ticket not forwaded for you");
        }

        this.assigneeId = userId;
        this.chatId = chatId;
        this.status = StatusEnum.PROGRESS;
        this.updatedAt = Instant.now();
    }

    public void resolve(UUID actorId) {
        if (!this.status.equals(StatusEnum.PROGRESS)) {
            throw new InvalidTicketStatusException("You cannot resolve this ticket");
        }

        if (!this.assigneeId.equals(actorId)) {
            throw new TicketOwnerShipException("Ticket not assigned for you");
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

    public Boolean canAccessChat(UUID actorId) {
        return actorId.equals(this.requesterId) || actorId.equals(this.assigneeId);
    }

}
