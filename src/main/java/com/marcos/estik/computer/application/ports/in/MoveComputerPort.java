package com.marcos.estik.computer.application.ports.in;

import java.util.UUID;

import com.marcos.estik.computer.application.command.MoveComputerCommand;
import com.marcos.estik.computer.domain.Computer;

public interface MoveComputerPort {
    Computer execute(UUID computerId, MoveComputerCommand command);
}
