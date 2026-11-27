package com.marcos.estik.solution.adapter.in.web.response;

import java.time.Instant;
import java.util.UUID;

public record SolutionPostSummaryResponse(
    UUID id,
    String title,
    SolutionUserResponse author,
    SolutionSpecializationResponse specialization,
    Instant createdAt
) {
    
}
