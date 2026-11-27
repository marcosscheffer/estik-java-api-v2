package com.marcos.estik.solution.adapter.in.web.response;

import java.time.Instant;
import java.util.UUID;

public record SolutionResponseSummaryResponse(
    UUID id,
    SolutionUserResponse author,
    String message,
    SolutionFileResponse file,
    Instant createdAt,
    Instant updatedAt
) {
    
}
