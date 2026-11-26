package com.marcos.estik.purchase.application.port.in;

import java.util.UUID;

public interface DeletePurchaseByIdPort {
    
    void execute(UUID id);

}
