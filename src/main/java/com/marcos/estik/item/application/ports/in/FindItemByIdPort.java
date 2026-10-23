package com.marcos.estik.item.application.ports.in;

import java.util.UUID;

import com.marcos.estik.item.application.view.ItemView;

public interface FindItemByIdPort {
    ItemView execute(UUID id);
}
