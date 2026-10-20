package com.marcos.estik.computerhistory.adapter.out.departament;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.marcos.estik.computerhistory.application.ports.out.LoadComputerHistoryExistsDepartamentByIdPort;
import com.marcos.estik.departament.application.ports.in.ExistsDepartamentByIdPort;

import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class LoadComputerHistoryExistsDepartamentByIdAdapter implements LoadComputerHistoryExistsDepartamentByIdPort {

    private final ExistsDepartamentByIdPort existsDepartamentByIdPort;

    @Override
    public Boolean existsById(UUID id) {
        return existsDepartamentByIdPort.execute(id);
    }
}
