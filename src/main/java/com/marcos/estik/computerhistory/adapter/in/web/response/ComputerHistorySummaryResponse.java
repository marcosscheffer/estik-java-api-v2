package com.marcos.estik.computerhistory.adapter.in.web.response;

import java.util.UUID;

import com.marcos.estik.computerhistory.domain.DirectionEnum;

public record ComputerHistorySummaryResponse(
    UUID id,
    String serviceDescription,
    ComputerHistoryComputerResponse computer,
    DirectionEnum direction
) {
    
}
