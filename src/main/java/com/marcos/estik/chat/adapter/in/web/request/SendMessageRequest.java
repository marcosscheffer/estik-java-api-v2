package com.marcos.estik.chat.adapter.in.web.request;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;

public record SendMessageRequest(
    UUID replyToId,
    @NotBlank 
    String content
) {
}
