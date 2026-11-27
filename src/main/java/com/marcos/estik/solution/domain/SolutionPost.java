package com.marcos.estik.solution.domain;

import java.time.Instant;
import java.util.UUID;

import com.marcos.estik.solution.domain.exception.SolutionForbiddenException;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter 
@AllArgsConstructor 
public class SolutionPost {
    private UUID id;
    private UUID authorId;
    private UUID specializationId;
    private String title;
    private String description;
    private UUID fileId;
    private Instant createdAt;
    private Instant updatedAt;

    public static SolutionPost create(
        UUID authorId,
        UUID specializationId,
        String title,
        String description,
        UUID fileId
    ) {
        return new SolutionPost(
            UUID.randomUUID(), 
            authorId, 
            specializationId, 
            title, 
            description, 
            fileId, 
            Instant.now(), 
            Instant.now()    
        );
    }


    public  void update(
        UUID actorId,
        String title,
        String description
    ) {
        if (!actorId.equals(authorId)) {
            throw new SolutionForbiddenException("Post does not belong to you");
        }

        this.title = title;
        this.description = description;
        this.updatedAt = Instant.now();
    }
}
