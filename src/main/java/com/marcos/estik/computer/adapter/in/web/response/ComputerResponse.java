package com.marcos.estik.computer.adapter.in.web.response;

import java.time.Instant;
import java.util.UUID;

import com.marcos.estik.computer.domain.OsEnum;
import com.marcos.estik.computer.domain.StorageTypeEnum;

public record ComputerResponse(
    UUID id,
    String name,
    String cpu,
    String ram,
    OsEnum os,
    StorageTypeEnum storageType,
    String storage,
    ComputerFacilitySummaryResponse facility,
    ComputerDepartamentSummaryResponse departament,
    Instant createdAt,
    Instant updatedAt
) {
    
}
