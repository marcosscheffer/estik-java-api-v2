package com.marcos.estik.computerhistory.application.ports.in;

import java.util.UUID;

import com.marcos.estik.computerhistory.domain.ComputerHistory;

public interface FindComputerHistoryByIdPort {
    ComputerHistory execute(UUID id);
}
