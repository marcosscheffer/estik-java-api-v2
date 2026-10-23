package com.marcos.estik.computer.application.ports.in;

import com.marcos.estik.computer.application.command.CreateComputerCommand;
import com.marcos.estik.computer.application.view.ComputerView;

public interface CreateComputerPort {

    ComputerView execute(CreateComputerCommand command);

}
