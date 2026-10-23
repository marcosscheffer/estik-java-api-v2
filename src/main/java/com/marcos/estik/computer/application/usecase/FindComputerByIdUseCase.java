package com.marcos.estik.computer.application.usecase;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.marcos.estik.computer.application.ports.in.FindComputerByIdPort;
import com.marcos.estik.computer.application.ports.out.ComputerRepositoryPort;
import com.marcos.estik.computer.application.ports.out.LoadComputerFindDepartamentByIdPort;
import com.marcos.estik.computer.application.ports.out.LoadComputerFindFacilityByIdPort;
import com.marcos.estik.computer.application.view.ComputerView;
import com.marcos.estik.computer.domain.Computer;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class FindComputerByIdUseCase implements FindComputerByIdPort {

    private final ComputerRepositoryPort computerRepositoryPort;
    private final LoadComputerFindDepartamentByIdPort loadComputerFindDepartamentByIdPort;
    private final LoadComputerFindFacilityByIdPort loadComputerFindFacilityByIdPort;

    @Override
    public ComputerView execute(UUID id) {
        Computer computer = computerRepositoryPort.findComputerById(id);

        return new ComputerView(
            computer,
            loadComputerFindFacilityByIdPort.execute(computer.getFacilityId()),
            loadComputerFindDepartamentByIdPort.execute(computer.getDepartamentId())
        );
    }
}
