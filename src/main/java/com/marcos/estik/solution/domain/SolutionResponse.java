package com.marcos.estik.solution.domain;

import java.time.Instant;
import java.util.UUID;

import com.marcos.estik.solution.domain.exception.SolutionForbiddenException;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter 
@AllArgsConstructor 
public class SolutionResponse {
    private UUID id;
    private UUID authorId;
    private UUID solutionPostId;
    private String message;
    private UUID fileId;
    private Instant createdAt;
    private Instant updatedAt;

    public static SolutionResponse create(
        UUID authorId,
        UUID solutionPostId,
        String message,
        UUID fileId
    ) {
        return new SolutionResponse(
            UUID.randomUUID(), 
            authorId, 
            solutionPostId, 
            message, 
            fileId, 
            Instant.now(), 
            Instant.now()
        );
    }

    public void update(String message, UUID actorId) {
        if (!this.authorId.equals(actorId)) {
            throw new SolutionForbiddenException("Does not belong to you");
        }

        this.message = message;
        this.updatedAt = Instant.now();
    }
}
