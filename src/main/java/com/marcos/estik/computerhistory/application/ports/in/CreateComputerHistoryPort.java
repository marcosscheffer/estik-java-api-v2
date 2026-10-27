package com.marcos.estik.computerhistory.application.ports.in;

import com.marcos.estik.computerhistory.application.command.CreateComputerHistoryCommand;
import com.marcos.estik.computerhistory.application.view.ComputerHistoryView;

public interface CreateComputerHistoryPort {
    ComputerHistoryView execute(CreateComputerHistoryCommand command);
}
