package com.marcos.estik.computerhistory.application.ports.in;

import java.util.UUID;

import com.marcos.estik.computerhistory.application.view.ComputerHistoryView;

public interface FindComputerHistoryByIdPort {
    ComputerHistoryView execute(UUID id);
}
