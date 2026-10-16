package com.marcos.estik.computer.application.command;

import java.util.UUID;

import com.marcos.estik.computer.domain.OsEnum;
import com.marcos.estik.computer.domain.StorageTypeEnum;

public record CreateComputerCommand(
    String name,
    String cpu,
    String ram,
    OsEnum os,
    StorageTypeEnum storageType,
    String storage,
    UUID facilityId,
    UUID departamentId
) {
    
}
