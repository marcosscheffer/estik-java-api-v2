package com.marcos.estik.computer.application.usecase;

import org.springframework.stereotype.Service;

import com.marcos.estik.computer.application.command.SearchComputerCommand;
import com.marcos.estik.computer.application.ports.in.SearchComputerPort;
import com.marcos.estik.computer.application.ports.out.ComputerRepositoryPort;
import com.marcos.estik.computer.domain.Computer;
import com.marcos.estik.shared.application.pagination.PageResult;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class SearchComputerUseCase implements SearchComputerPort {

    private final ComputerRepositoryPort computerRepositoryPort;

    @Override
    public PageResult<Computer> execute(SearchComputerCommand command) {
        return computerRepositoryPort.searchComputer(
            command.facilityId(),
            command.departamentId(),
            command.name(),
            command.page()
        );
    }
    
}
