package com.marcos.estik.item.application.ports.in;

import java.util.UUID;

import com.marcos.estik.item.application.command.UpdateItemCommand;
import com.marcos.estik.item.application.view.ItemView;

public interface UpdateItemPort {
    ItemView execute(UUID id, UpdateItemCommand command);
}
