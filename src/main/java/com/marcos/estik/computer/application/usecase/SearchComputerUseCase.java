package com.marcos.estik.computer.application.usecase;

import org.springframework.stereotype.Service;

import com.marcos.estik.computer.application.command.SearchComputerCommand;
import com.marcos.estik.computer.application.ports.in.SearchComputerPort;
import com.marcos.estik.computer.application.ports.out.ComputerRepositoryPort;
import com.marcos.estik.computer.application.ports.out.LoadComputerFindDepartamentByIdPort;
import com.marcos.estik.computer.application.ports.out.LoadComputerFindFacilityByIdPort;
import com.marcos.estik.computer.application.view.ComputerView;
import com.marcos.estik.computer.domain.Computer;
import com.marcos.estik.shared.application.pagination.PageResult;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class SearchComputerUseCase implements SearchComputerPort {

    private final ComputerRepositoryPort computerRepositoryPort;
    private final LoadComputerFindDepartamentByIdPort loadComputerFindDepartamentByIdPort;
    private final LoadComputerFindFacilityByIdPort loadComputerFindFacilityByIdPort;

    @Override
    public PageResult<ComputerView> execute(SearchComputerCommand command) {
        PageResult<Computer> computers =  computerRepositoryPort.searchComputer(
            command.facilityId(),
            command.departamentId(),
            command.name(),
            command.page()
        );

        return computers.map(computer -> new ComputerView(
                computer,
                loadComputerFindFacilityByIdPort.execute(computer.getFacilityId()),
                loadComputerFindDepartamentByIdPort.execute(computer.getDepartamentId())
            )
        );
    }
}
