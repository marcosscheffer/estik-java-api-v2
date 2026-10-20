package com.marcos.estik.item.application.ports.in;

import java.util.UUID;

import com.marcos.estik.item.domain.Item;

public interface FindItemByIdPort {
    Item execute(UUID id);
}
