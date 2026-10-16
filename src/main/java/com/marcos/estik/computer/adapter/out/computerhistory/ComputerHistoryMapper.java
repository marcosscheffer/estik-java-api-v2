package com.marcos.estik.computer.adapter.out.computerhistory;

import org.mapstruct.Mapper;

import com.marcos.estik.computer.application.command.LoadCreateComputerHistoryCommand;
import com.marcos.estik.computerhistory.application.command.CreateComputerHistoryCommand;

@Mapper(componentModel = "spring")
public interface ComputerHistoryMapper {
    CreateComputerHistoryCommand toCreateComputerHistoryCommand(LoadCreateComputerHistoryCommand command);
}
