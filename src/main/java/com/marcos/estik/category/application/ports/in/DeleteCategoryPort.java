package com.marcos.estik.category.application.ports.in;

import java.util.UUID;

public interface DeleteCategoryPort {
    
    void execute(UUID id);

}
