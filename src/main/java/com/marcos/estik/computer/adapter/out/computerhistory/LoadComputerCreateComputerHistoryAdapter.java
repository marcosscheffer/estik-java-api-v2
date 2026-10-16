package com.marcos.estik.computer.adapter.out.computerhistory;

import org.springframework.stereotype.Component;

import com.marcos.estik.computer.application.command.LoadCreateComputerHistoryCommand;
import com.marcos.estik.computer.application.ports.out.LoadComputerCreateComputerHistoryPort;
import com.marcos.estik.computerhistory.application.ports.in.CreateComputerHistoryPort;

import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class LoadComputerCreateComputerHistoryAdapter implements LoadComputerCreateComputerHistoryPort {

    private final CreateComputerHistoryPort createComputerHistoryPort;
    private final ComputerHistoryMapper computerHistoryMapper;

    @Override
    public void CreateComputerHistory(LoadCreateComputerHistoryCommand command) {
        createComputerHistoryPort.execute(computerHistoryMapper.toCreateComputerHistoryCommand(command));
    }
    
}
