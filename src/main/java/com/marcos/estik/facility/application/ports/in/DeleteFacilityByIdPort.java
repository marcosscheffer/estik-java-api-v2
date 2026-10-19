package com.marcos.estik.facility.application.ports.in;

import java.util.UUID;

public interface DeleteFacilityByIdPort {

    void execute(UUID id);
    
}