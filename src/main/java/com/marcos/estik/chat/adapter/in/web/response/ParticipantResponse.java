package com.marcos.estik.chat.adapter.in.web.response;

import java.time.Instant;
import java.util.UUID;

public record ParticipantResponse(
    UUID id,
    UserSummaryResponse userId,
    Instant joinedAt
) {
}
