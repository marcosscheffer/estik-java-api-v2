package com.marcos.estik.computerhistory.application.usecases;

import org.springframework.stereotype.Service;

import com.marcos.estik.computerhistory.adapter.out.departament.LoadComputerHistoryExistsDepartamentByIdAdapter;
import com.marcos.estik.computerhistory.application.command.CreateComputerHistoryCommand;
import com.marcos.estik.computerhistory.application.ports.in.CreateComputerHistoryPort;
import com.marcos.estik.computerhistory.application.ports.out.ComputerHistoryRepositoryPort;
import com.marcos.estik.computerhistory.domain.ComputerHistory;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class CreateComputerHistoryUseCase implements CreateComputerHistoryPort {

    private final ComputerHistoryRepositoryPort computerHistoryRepositoryPort;
    private final LoadComputerHistoryExistsDepartamentByIdAdapter loadComputerHistoryExistsDepartamentByIdPort;


    @Override
    public ComputerHistory execute(CreateComputerHistoryCommand command) {
        if(!loadComputerHistoryExistsDepartamentByIdPort.existsById(command.departamentId())) {
            throw new EntityNotFoundException("Departament not found");
        }
        ComputerHistory computerHistory = ComputerHistory.create(
            command.serviceDescription(), 
            command.direction(), 
            command.computerId(), 
            command.departamentId()
        );

        computerHistoryRepositoryPort.save(computerHistory);
        return computerHistory;
    }
}
