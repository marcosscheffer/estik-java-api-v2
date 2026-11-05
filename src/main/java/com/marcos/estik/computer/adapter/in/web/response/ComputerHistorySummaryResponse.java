package com.marcos.estik.computer.adapter.in.web.response;

import java.time.Instant;
import java.util.UUID;

import com.marcos.estik.computer.domain.DirectionEnum;

public record ComputerHistorySummaryResponse(
    UUID id,
    String serviceDescription,
    ComputerHistoryComputerResponse computer,
    DirectionEnum direction,
    Instant createdAt
) {
    
}
