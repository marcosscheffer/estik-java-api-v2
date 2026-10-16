package com.marcos.estik.computer.adapter.out.departament;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.marcos.estik.computer.application.ports.out.LoadComputerVerifyDepartamentExistsByIdPort;
import com.marcos.estik.departament.application.ports.in.VerifyDepartamentExistsByIdPort;

import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class LoadComputerVerifyDepartamentExistsByIdAdapter implements LoadComputerVerifyDepartamentExistsByIdPort {
    
    private final VerifyDepartamentExistsByIdPort verifyDepartamentExistsByIdPort;

    @Override
    public void existsById(UUID id) {
        verifyDepartamentExistsByIdPort.execute(id);
    }
}
