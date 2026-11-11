package com.marcos.estik.specialization.application.ports.in;

import java.util.UUID;

public interface DeleteSpecializationPort {

    void execute(UUID id);

}