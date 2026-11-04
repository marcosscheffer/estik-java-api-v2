package com.marcos.estik.chat.adapter.out.sse;

import java.util.UUID;


public record ParticipantSummaryPayload(
    UUID id,
    UserSummaryPayload userId
) {
    
}
