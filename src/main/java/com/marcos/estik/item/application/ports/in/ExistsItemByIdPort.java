package com.marcos.estik.item.application.ports.in;

import java.util.UUID;

public interface ExistsItemByIdPort {

    Boolean execute(UUID id);
    
}
