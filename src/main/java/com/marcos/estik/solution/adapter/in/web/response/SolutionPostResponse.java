package com.marcos.estik.solution.adapter.in.web.response;

import java.time.Instant;
import java.util.UUID;

public record SolutionPostResponse(
    UUID id,
    String title,
    SolutionUserResponse author,
    SolutionSpecializationResponse specialization,
    String description,
    SolutionFileResponse file,
    Instant createdAt,
    Instant updatedAt

) {
    
}
