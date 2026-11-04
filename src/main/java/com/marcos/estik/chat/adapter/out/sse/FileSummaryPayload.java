package com.marcos.estik.chat.adapter.out.sse;

import java.util.UUID;

public record FileSummaryPayload(
    UUID id,
    String fileUrl
) {
    
}
