package com.marcos.estik.departament.adapter.out.facility;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.marcos.estik.departament.application.ports.out.LoadVerifyFacilityByIdPort;
import com.marcos.estik.facility.application.ports.in.VerifyFacilityExistsByIdPort;

import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class LoadVerifyFacilityByIdAdapter implements LoadVerifyFacilityByIdPort {

    private final VerifyFacilityExistsByIdPort verifyFacilityExistsByIdPort;

    @Override
    public void verifyById(UUID id) {
        verifyFacilityExistsByIdPort.execute(id);
    }
    
}
