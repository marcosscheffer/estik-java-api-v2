package com.marcos.estik.computer.application.ports.out;

import java.util.UUID;

import com.marcos.estik.computer.domain.ComputerHistory;
import com.marcos.estik.computer.domain.DirectionEnum;
import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.application.pagination.PageResult;

public interface ComputerHistoryRepositoryPort {
    ComputerHistory save(ComputerHistory computerHistory);

    ComputerHistory findComputerHistoryById(UUID id);

    PageResult<ComputerHistory> SearchComputerHistory(
        DirectionEnum direction,
        UUID computerId,
        UUID departamentId,
        PageQuery page
    );
}
