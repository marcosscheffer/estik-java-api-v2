package com.marcos.estik.computer.application.command;

import com.marcos.estik.computer.domain.DirectionEnum;
import com.marcos.estik.computer.domain.OsEnum;
import com.marcos.estik.computer.domain.StorageTypeEnum;

public record UpdateComputerCommand(
    String cpu,
    String ram,
    OsEnum os,
    StorageTypeEnum storageType,
    String storage,
    String serviceDescription,
    DirectionEnum direction
) {
    
}

