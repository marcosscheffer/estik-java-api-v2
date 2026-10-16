package com.marcos.estik.computer.application.ports.in;

import com.marcos.estik.computer.application.command.CreateComputerCommand;
import com.marcos.estik.computer.domain.Computer;

public interface CreateComputerPort {

    Computer execute(CreateComputerCommand command);

}
