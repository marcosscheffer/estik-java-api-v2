package com.marcos.estik.solution.adapter.out.persistence.solutionpost;

import java.time.Instant;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity 
@Table(name = "solution_posts")
@Getter 
@Setter 
public class SolutionPostEntity {
    @Id 
    private UUID id;
    
    @Column(name = "author_id")
    private UUID authorId;

    @Column(name = "specialization_id")
    private UUID specializationId;
    private String title;
    private String description;

    @Column(name = "file_id")
    private UUID fileId;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false, updatable = true)
    private Instant updatedAt;
}
