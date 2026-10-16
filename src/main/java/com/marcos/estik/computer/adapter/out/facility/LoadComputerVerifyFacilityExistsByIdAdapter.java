package com.marcos.estik.computer.adapter.out.facility;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.marcos.estik.computer.application.ports.out.LoadComputerVerifyFacilityExistsByIdPort;
import com.marcos.estik.facility.application.ports.in.VerifyFacilityExistsByIdPort;

import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class LoadComputerVerifyFacilityExistsByIdAdapter implements LoadComputerVerifyFacilityExistsByIdPort {

    private final VerifyFacilityExistsByIdPort verifyFacilityExistsByIdPort;

    @Override
    public void existsById(UUID id) {
        verifyFacilityExistsByIdPort.execute(id);
    }
}
