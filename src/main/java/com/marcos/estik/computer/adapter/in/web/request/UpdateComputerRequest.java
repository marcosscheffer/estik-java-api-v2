package com.marcos.estik.computer.adapter.in.web.request;

import com.marcos.estik.computer.domain.OsEnum;
import com.marcos.estik.computer.domain.StorageTypeEnum;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateComputerRequest(
    @NotBlank 
    String cpu,
    @NotBlank
    String ram,
    @NotNull
    OsEnum os,
    @NotNull
    StorageTypeEnum storageType,
    @NotBlank
    String storage,
    @NotBlank
    String serviceDescription,
    @NotBlank
    String direction
) {
}
