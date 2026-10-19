package com.marcos.estik.computer.application.usecase;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.marcos.estik.computer.application.ports.in.DeleteComputerByIdPort;
import com.marcos.estik.computer.application.ports.out.ComputerRepositoryPort;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class DeleteComputerUseCase implements DeleteComputerByIdPort {

    private final ComputerRepositoryPort computerRepositoryPort;

    @Override
    public void execute(UUID id) {
        computerRepositoryPort.deleteComputerById(id);
    }
    
}
