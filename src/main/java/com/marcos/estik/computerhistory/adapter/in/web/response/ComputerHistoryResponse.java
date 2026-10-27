package com.marcos.estik.computerhistory.adapter.in.web.response;

import java.time.Instant;
import java.util.UUID;

import com.marcos.estik.computerhistory.domain.DirectionEnum;

public record ComputerHistoryResponse(
    UUID id,
    String serviceDescription,
    DirectionEnum direction,
    ComputerHistoryComputerResponse computer,
    UUID departamentId,
    Instant createdAt
) {
    
}
