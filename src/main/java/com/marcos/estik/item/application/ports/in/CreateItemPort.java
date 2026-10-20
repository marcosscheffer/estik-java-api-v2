package com.marcos.estik.item.application.ports.in;

import com.marcos.estik.item.application.command.CreateItemCommand;
import com.marcos.estik.item.domain.Item;

public interface CreateItemPort {
    Item execute(CreateItemCommand command);
}
