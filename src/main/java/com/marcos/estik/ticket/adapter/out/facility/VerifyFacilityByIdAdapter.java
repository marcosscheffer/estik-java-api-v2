package com.marcos.estik.ticket.adapter.out.facility;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.marcos.estik.facility.application.ports.in.VerifyFacilityExistsByIdPort;
import com.marcos.estik.ticket.application.ports.out.VerifyFacilityExistsPort;

import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class VerifyFacilityByIdAdapter implements VerifyFacilityExistsPort{

    private final VerifyFacilityExistsByIdPort verifyFacilityExistsByIdPort;

    @Override
    public void existsById(UUID id) {
        verifyFacilityExistsByIdPort.execute(id);
    }
    
}
