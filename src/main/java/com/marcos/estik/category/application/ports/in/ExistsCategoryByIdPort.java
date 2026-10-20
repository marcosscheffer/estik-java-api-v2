package com.marcos.estik.category.application.ports.in;

import java.util.UUID;

public interface ExistsCategoryByIdPort {
    Boolean execute(UUID id);
}
