package com.marcos.estik.computer.application.usecase;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marcos.estik.computer.application.command.LoadCreateComputerHistoryCommand;
import com.marcos.estik.computer.application.command.UpdateComputerCommand;
import com.marcos.estik.computer.application.ports.in.UpdateComputerPort;
import com.marcos.estik.computer.application.ports.out.ComputerRepositoryPort;
import com.marcos.estik.computer.application.ports.out.LoadComputerCreateComputerHistoryPort;
import com.marcos.estik.computer.application.ports.out.LoadComputerVerifyDepartamentExistsByIdPort;
import com.marcos.estik.computer.application.ports.out.LoadComputerVerifyFacilityExistsByIdPort;
import com.marcos.estik.computer.domain.Computer;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class UpdateComputerUseCase implements UpdateComputerPort {

    private final LoadComputerVerifyDepartamentExistsByIdPort loadVerifyDepartamentExistsByIdPort;
    private final LoadComputerVerifyFacilityExistsByIdPort loadVerifyFacilityExistsByIdPort;
    private final LoadComputerCreateComputerHistoryPort loadCreateComputerHistoryPort;
    private final ComputerRepositoryPort computerRepositoryPort;

    @Override
    @Transactional
    public Computer execute(UpdateComputerCommand command, UUID computerId) {
        loadVerifyDepartamentExistsByIdPort.existsById(command.departamentId());
        loadVerifyFacilityExistsByIdPort.existsById(command.facilityId());

        loadCreateComputerHistoryPort.CreateComputerHistory(
            new LoadCreateComputerHistoryCommand(
                command.serviceDescription(),
                command.direction(),
                computerId,
                command.departamentId()
            )
        );

        Computer computer = computerRepositoryPort.findComputerById(computerId);

        computer.setCpu(command.cpu());
        computer.setRam(command.ram());
        computer.setOs(command.os());
        computer.setStorage(command.storage());
        computer.setStorageType(command.storageType());
        computer.setDepartamentId(command.departamentId());
        computer.setFacilityId(command.facilityId());

        return computerRepositoryPort.save(computer);
    }
    
}
