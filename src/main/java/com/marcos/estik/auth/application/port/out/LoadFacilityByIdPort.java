package com.marcos.estik.auth.application.port.out;

import java.util.UUID;

import com.marcos.estik.auth.domain.FacilityAuth;

public interface LoadFacilityByIdPort {
    FacilityAuth findById(UUID id);
}
