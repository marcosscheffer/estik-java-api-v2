package com.marcos.estik.computer.application.usecase;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marcos.estik.computer.application.command.CreateComputerCommand;
import com.marcos.estik.computer.application.command.LoadCreateComputerHistoryCommand;
import com.marcos.estik.computer.application.ports.in.CreateComputerPort;
import com.marcos.estik.computer.application.ports.out.ComputerRepositoryPort;
import com.marcos.estik.computer.application.ports.out.LoadComputerCreateComputerHistoryPort;
import com.marcos.estik.computer.application.ports.out.LoadComputerFindDepartamentByIdPort;
import com.marcos.estik.computer.application.ports.out.LoadComputerFindFacilityByIdPort;
import com.marcos.estik.computer.application.view.ComputerDepartamentInfo;
import com.marcos.estik.computer.application.view.ComputerFacilityInfo;
import com.marcos.estik.computer.application.view.ComputerView;
import com.marcos.estik.computer.domain.Computer;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class CreateComputerUseCase implements CreateComputerPort {

    private final ComputerRepositoryPort computerRepositoryPort;
    private final LoadComputerFindDepartamentByIdPort loadComputerFindDepartamentByIdPort;
    private final LoadComputerFindFacilityByIdPort loadComputerFindFacilityByIdPort;
    private final LoadComputerCreateComputerHistoryPort loadComputerCreateComputerHistoryPort;

    @Override
    @Transactional
    public ComputerView execute(CreateComputerCommand command) {
        ComputerDepartamentInfo departamentInfo = loadComputerFindDepartamentByIdPort.execute(command.departamentId());
        ComputerFacilityInfo facilityInfo = loadComputerFindFacilityByIdPort.execute(command.facilityId());

        Computer computer = Computer.create(
            command.name(),
            command.cpu(),
            command.ram(),
            command.os(),
            command.storageType(),
            command.storage(),
            command.facilityId(),
            command.departamentId()
        );
        
        computerRepositoryPort.save(computer);

        loadComputerCreateComputerHistoryPort.CreateComputerHistory(
            new LoadCreateComputerHistoryCommand(
                "Created",
                "SENT",
                computer.getId(),
                computer.getDepartamentId()
            )
        );

        return new ComputerView(
            computer,
            facilityInfo,
            departamentInfo
        );
    }
}
