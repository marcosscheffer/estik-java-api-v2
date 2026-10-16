package com.marcos.estik.computer.application.ports.in;

import java.util.UUID;

import com.marcos.estik.computer.application.command.UpdateComputerCommand;
import com.marcos.estik.computer.domain.Computer;

public interface UpdateComputerPort {
    Computer execute(UpdateComputerCommand command, UUID computerId);
}
