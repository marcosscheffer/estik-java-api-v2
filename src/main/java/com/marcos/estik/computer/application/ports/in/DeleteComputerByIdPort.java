package com.marcos.estik.computer.application.ports.in;

import java.util.UUID;

public interface DeleteComputerByIdPort {
    void execute(UUID id);
}
