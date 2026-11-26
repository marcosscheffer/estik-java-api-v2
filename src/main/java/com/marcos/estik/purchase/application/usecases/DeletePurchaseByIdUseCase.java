package com.marcos.estik.purchase.application.usecases;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.marcos.estik.purchase.application.port.in.DeletePurchaseByIdPort;
import com.marcos.estik.purchase.application.port.out.PurchaseRepositoryPort;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class DeletePurchaseByIdUseCase implements DeletePurchaseByIdPort {
    
    private final PurchaseRepositoryPort purchaseRepositoryPort;

    @Override
    public void execute(UUID id) {
        purchaseRepositoryPort.deleteById(id);
    }
    
}
