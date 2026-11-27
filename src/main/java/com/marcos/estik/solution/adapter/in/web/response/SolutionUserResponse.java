package com.marcos.estik.solution.adapter.in.web.response;

import java.util.UUID;

public record SolutionUserResponse(
    UUID id,
    String username
) {
    
}
