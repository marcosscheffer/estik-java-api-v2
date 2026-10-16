package com.marcos.estik.computer.adapter.in.web.request;

import java.util.UUID;

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
    @NotNull
    UUID facilityId,
    @NotNull
    UUID departamentId,
    @NotBlank
    String serviceDescription,
    @NotBlank
    String direction
) {
}
