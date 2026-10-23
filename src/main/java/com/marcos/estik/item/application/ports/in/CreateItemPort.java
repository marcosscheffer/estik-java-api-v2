package com.marcos.estik.item.application.ports.in;

import com.marcos.estik.item.application.command.CreateItemCommand;
import com.marcos.estik.item.application.view.ItemView;

public interface CreateItemPort {
    ItemView execute(CreateItemCommand command);
}
