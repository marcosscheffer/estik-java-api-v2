package com.marcos.estik.item.application.ports.out;

import java.util.UUID;

public interface LoadItemExistsCategoryByIdPort {
    Boolean existsById(UUID categoryId);
}
