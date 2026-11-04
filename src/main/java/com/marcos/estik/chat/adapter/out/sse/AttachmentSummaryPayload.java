package com.marcos.estik.chat.adapter.out.sse;

import java.util.UUID;

public record AttachmentSummaryPayload(
    UUID id,
    FileSummaryPayload file
) {
    
}
