package com.marcos.estik.computer.application.ports.in;

import java.util.UUID;

import com.marcos.estik.computer.application.command.UpdateComputerCommand;
import com.marcos.estik.computer.application.view.ComputerView;

public interface UpdateComputerPort {
    ComputerView execute(UpdateComputerCommand command, UUID computerId);
}
