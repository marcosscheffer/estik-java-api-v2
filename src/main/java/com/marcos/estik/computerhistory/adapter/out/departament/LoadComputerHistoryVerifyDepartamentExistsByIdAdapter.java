package com.marcos.estik.computerhistory.adapter.out.departament;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.marcos.estik.computerhistory.application.ports.out.LoadComputerHistoryVerifyDepartamentExistsByIdPort;
import com.marcos.estik.departament.application.ports.in.VerifyDepartamentExistsByIdPort;

import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class LoadComputerHistoryVerifyDepartamentExistsByIdAdapter implements LoadComputerHistoryVerifyDepartamentExistsByIdPort {

    private final VerifyDepartamentExistsByIdPort verifyDepartamentExistsByIdPort;

    @Override
    public void existsById(UUID id) {
        verifyDepartamentExistsByIdPort.execute(id);
    }
}
