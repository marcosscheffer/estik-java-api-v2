package com.marcos.estik.computer.application.ports.out;

import com.marcos.estik.computer.application.command.LoadCreateComputerHistoryCommand;

public interface LoadComputerCreateComputerHistoryPort {
    void CreateComputerHistory(LoadCreateComputerHistoryCommand command);
}
