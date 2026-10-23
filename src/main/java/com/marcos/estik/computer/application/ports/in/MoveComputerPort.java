package com.marcos.estik.computer.application.ports.in;

import java.util.UUID;

import com.marcos.estik.computer.application.command.MoveComputerCommand;
import com.marcos.estik.computer.application.view.ComputerView;

public interface MoveComputerPort {
    ComputerView execute(UUID computerId, MoveComputerCommand command);
}
