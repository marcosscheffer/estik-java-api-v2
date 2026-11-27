package com.marcos.estik.solution.adapter.out.persistence.solutionresponse;

import java.time.Instant;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity 
@Table(name = "solution_responses")
@Getter 
@Setter
public class SolutionResponseEntity {
    @Id 
    private UUID id;

    @Column(name = "author_id")
    private UUID authorId;

    @Column(name = "solution_post_id")
    private UUID solutionPostId;
    private String message;

    @Column(name = "file_id")
    private UUID fileId;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;
    
    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false, updatable = true)
    private Instant updatedAt;
}
