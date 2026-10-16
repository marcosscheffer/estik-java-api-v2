package com.marcos.estik.computer.adapter.in.web.mapper;

import org.mapstruct.Mapper;

import com.marcos.estik.computer.adapter.in.web.request.CreateComputerRequest;
import com.marcos.estik.computer.adapter.in.web.request.UpdateComputerRequest;
import com.marcos.estik.computer.adapter.in.web.response.ComputerResponse;
import com.marcos.estik.computer.adapter.in.web.response.ComputerSummaryResponse;
import com.marcos.estik.computer.application.command.CreateComputerCommand;
import com.marcos.estik.computer.application.command.UpdateComputerCommand;
import com.marcos.estik.computer.domain.Computer;

@Mapper(componentModel = "spring")
public interface ComputerWebMapper {
    
    ComputerSummaryResponse toSummaryResponse(Computer computer);

    ComputerResponse toResponse(Computer computer);

    CreateComputerCommand toCreateComputerCommand(CreateComputerRequest request);

    UpdateComputerCommand toUpdateComputerCommand(UpdateComputerRequest request);
}
