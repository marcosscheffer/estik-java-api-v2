package com.marcos.estik.computer.adapter.out.departament;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.marcos.estik.computer.application.ports.out.LoadComputerExistsDepartamentByIdPort;
import com.marcos.estik.departament.application.ports.in.ExistsDepartamentByIdPort;

import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class LoadComputerExistsDepartamentByIdAdapter implements LoadComputerExistsDepartamentByIdPort {
    
    private final ExistsDepartamentByIdPort existsDepartamentByIdPort;

    @Override
    public Boolean existsById(UUID id) {
        return existsDepartamentByIdPort.execute(id);
    }
}
