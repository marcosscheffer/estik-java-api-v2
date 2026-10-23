package com.marcos.estik.computer.adapter.in.web.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.marcos.estik.computer.adapter.in.web.request.CreateComputerRequest;
import com.marcos.estik.computer.adapter.in.web.request.UpdateComputerRequest;
import com.marcos.estik.computer.adapter.in.web.request.moveComputerRequest;
import com.marcos.estik.computer.adapter.in.web.response.ComputerResponse;
import com.marcos.estik.computer.adapter.in.web.response.ComputerSummaryResponse;
import com.marcos.estik.computer.application.command.CreateComputerCommand;
import com.marcos.estik.computer.application.command.MoveComputerCommand;
import com.marcos.estik.computer.application.command.UpdateComputerCommand;
import com.marcos.estik.computer.application.view.ComputerView;

@Mapper(componentModel = "spring")
public interface ComputerWebMapper {

    @Mapping(target = ".", source = "computer")
    ComputerSummaryResponse toSummaryResponse(ComputerView computer);

    @Mapping(target = ".", source = "computer")
    ComputerResponse toResponse(ComputerView computer);

    CreateComputerCommand toCreateComputerCommand(CreateComputerRequest request);

    UpdateComputerCommand toUpdateComputerCommand(UpdateComputerRequest request);

    MoveComputerCommand toMoveComputerCommand(moveComputerRequest request);
}
