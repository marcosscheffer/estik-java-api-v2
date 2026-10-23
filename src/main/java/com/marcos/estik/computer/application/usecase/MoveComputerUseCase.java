package com.marcos.estik.computer.application.usecase;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marcos.estik.computer.application.command.LoadCreateComputerHistoryCommand;
import com.marcos.estik.computer.application.command.MoveComputerCommand;
import com.marcos.estik.computer.application.ports.in.MoveComputerPort;
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
@Transactional 
public class MoveComputerUseCase implements MoveComputerPort {

    private final ComputerRepositoryPort computerRepositoryPort;
    private final LoadComputerFindFacilityByIdPort loadComputerFindFacilityByIdPort;
    private final LoadComputerFindDepartamentByIdPort loadComputerFindDepartamentByIdPort;
    private final LoadComputerCreateComputerHistoryPort loadComputerCreateComputerHistoryPort;

    @Override
    public ComputerView execute(UUID computerId, MoveComputerCommand command) {
        ComputerDepartamentInfo departamentInfo = loadComputerFindDepartamentByIdPort.execute(command.departamentId());
        ComputerFacilityInfo facilityInfo = loadComputerFindFacilityByIdPort.execute(command.facilityId());

        Computer computer = computerRepositoryPort.findComputerById(computerId);
        computer.moveTo(command.facilityId(), command.departamentId());

        loadComputerCreateComputerHistoryPort.CreateComputerHistory(
            new LoadCreateComputerHistoryCommand(
                command.serviceDescription(),
                command.direction(),
                computerId,
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
