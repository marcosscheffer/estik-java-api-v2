package com.marcos.estik.computerhistory.application.usecases;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.marcos.estik.computerhistory.application.ports.in.FindComputerHistoryByIdPort;
import com.marcos.estik.computerhistory.application.ports.out.ComputerHistoryRepositoryPort;
import com.marcos.estik.computerhistory.domain.ComputerHistory;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class FindComputerHistoryByIdUseCase implements FindComputerHistoryByIdPort {

    private final ComputerHistoryRepositoryPort computerHistoryRepositoryPort;

    @Override
    public ComputerHistory execute(UUID id) {
        return computerHistoryRepositoryPort.findComputerHistoryById(id);
    }
    
}
