package com.marcos.estik.specialization.application.ports.in;

import java.util.UUID;

import com.marcos.estik.specialization.domain.Specialization;

public interface UpdateSpecializationPort {
    
    Specialization execute(UUID id, String name);

}
