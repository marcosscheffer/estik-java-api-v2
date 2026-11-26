package com.marcos.estik.solution.domain;

import java.time.Instant;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter 
@AllArgsConstructor 
public class SolutionTopic {
    private UUID id;
    private UUID authorId;
    private UUID solutionPostId;
    private String topic;
    private UUID fileId;
    private Instant createdAt;
    private Instant updatedAt;

    public static SolutionTopic create(
        UUID authorId,
        UUID solutionPostId,
        String topic,
        UUID fileId
    ) {
        return new SolutionTopic(
            UUID.randomUUID(), 
            authorId, 
            solutionPostId, 
            topic, 
            fileId, 
            Instant.now(), 
            Instant.now()
        );
    }
    
}
