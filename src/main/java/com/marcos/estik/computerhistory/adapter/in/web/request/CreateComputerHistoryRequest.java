package com.marcos.estik.computerhistory.adapter.in.web.request;

import java.util.UUID;

import com.marcos.estik.computerhistory.domain.DirectionEnum;

public record CreateComputerHistoryRequest(
    String serviceDescription,
    DirectionEnum direction,
    UUID computerId,
    UUID departamentId
) {
    
}
