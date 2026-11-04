package com.marcos.estik.chat.adapter.in.web.response;

import java.util.UUID;

public record AttachmentResponse(
    UUID id,
    ChatSummaryFileResponse file
) {
    
}
