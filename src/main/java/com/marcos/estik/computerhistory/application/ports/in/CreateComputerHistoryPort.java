package com.marcos.estik.computerhistory.application.ports.in;

import com.marcos.estik.computerhistory.application.command.CreateComputerHistoryCommand;
import com.marcos.estik.computerhistory.domain.ComputerHistory;

public interface CreateComputerHistoryPort {
    ComputerHistory execute(CreateComputerHistoryCommand command);
}
