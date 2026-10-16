package com.marcos.estik.computer.application.usecase;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.marcos.estik.computer.application.ports.in.FindComputerByIdPort;
import com.marcos.estik.computer.application.ports.out.ComputerRepositoryPort;
import com.marcos.estik.computer.domain.Computer;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class FindComputerByIdUseCase implements FindComputerByIdPort {

    private final ComputerRepositoryPort computerRepositoryPort;

    @Override
    public Computer execute(UUID id) {
        return computerRepositoryPort.findComputerById(id);
    }
    
}
