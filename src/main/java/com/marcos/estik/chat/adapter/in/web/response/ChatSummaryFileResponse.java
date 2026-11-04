package com.marcos.estik.chat.adapter.in.web.response;

import java.util.UUID;

public record ChatSummaryFileResponse(
    UUID id,
    String fileUrl,
    String fileType
) {
    
}
