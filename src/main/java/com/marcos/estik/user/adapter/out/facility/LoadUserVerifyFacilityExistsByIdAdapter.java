package com.marcos.estik.user.adapter.out.facility;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.marcos.estik.facility.application.ports.in.VerifyFacilityExistsByIdPort;
import com.marcos.estik.user.application.port.out.LoadUserVerifyFacilityExistsByIdPort;

import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class LoadUserVerifyFacilityExistsByIdAdapter implements LoadUserVerifyFacilityExistsByIdPort {

    private final VerifyFacilityExistsByIdPort verifyFacilityExistsByIdPort;

    @Override
    public void existsById(UUID id) {
        verifyFacilityExistsByIdPort.execute(id);
    }
    
}
