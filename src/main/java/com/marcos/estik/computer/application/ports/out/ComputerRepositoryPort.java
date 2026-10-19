package com.marcos.estik.computer.application.ports.out;

import java.util.UUID;

import com.marcos.estik.computer.domain.Computer;
import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.application.pagination.PageResult;

public interface ComputerRepositoryPort {
    
    Computer save(Computer computer);

    Computer findComputerById(UUID id);

    PageResult<Computer> searchComputer(
        UUID facilityId,
        UUID departamentId,
        String name,
        PageQuery page
    );

    void deleteComputerById(UUID id);

}
