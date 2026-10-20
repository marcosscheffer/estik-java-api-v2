package com.marcos.estik.item.application.ports.in;

import java.util.UUID;

import com.marcos.estik.item.application.command.UpdateItemCommand;
import com.marcos.estik.item.domain.Item;

public interface UpdateItemPort {
    Item execute(UUID id, UpdateItemCommand command);
}
