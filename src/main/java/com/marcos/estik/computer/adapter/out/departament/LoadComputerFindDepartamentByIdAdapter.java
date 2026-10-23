package com.marcos.estik.computer.adapter.out.departament;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.marcos.estik.computer.application.ports.out.LoadComputerFindDepartamentByIdPort;
import com.marcos.estik.computer.application.view.ComputerDepartamentInfo;
import com.marcos.estik.departament.application.ports.in.FindDepartamentByIdPort;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor 
public class LoadComputerFindDepartamentByIdAdapter implements LoadComputerFindDepartamentByIdPort {
    
    private final FindDepartamentByIdPort findDepartamentByIdPort;
    private final ComputerDepartamentMapper computerDepartamentMapper;
    
    @Override
    public ComputerDepartamentInfo execute(UUID id) {
        return computerDepartamentMapper.toInfo(findDepartamentByIdPort.execute(id));
    }
    
}
