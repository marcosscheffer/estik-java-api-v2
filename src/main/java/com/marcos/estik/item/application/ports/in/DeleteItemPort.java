package com.marcos.estik.item.application.ports.in;

import java.util.UUID;

public interface DeleteItemPort {
    
    void execute(UUID id);

}
