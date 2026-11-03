package com.marcos.estik.chat.adapter.in.web.response;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import com.marcos.estik.shared.application.pagination.PageResult;

public record ChatResponse(
    UUID id,
    Instant createdAt,
    PageResult<MessageResponse> messages,
    List<ParticipantResponse> participants
) {
}