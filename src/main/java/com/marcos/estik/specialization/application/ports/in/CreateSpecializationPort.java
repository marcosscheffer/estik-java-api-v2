package com.marcos.estik.specialization.application.ports.in;

import com.marcos.estik.specialization.domain.Specialization;

public interface CreateSpecializationPort {

    Specialization execute(String name);
    
}
