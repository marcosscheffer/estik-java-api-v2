package com.marcos.estik.computer.application.ports.in;

import java.util.UUID;

import com.marcos.estik.computer.application.view.ComputerHistoryView;


public interface FindComputerHistoryByIdPort {
    ComputerHistoryView execute(UUID id);
}
