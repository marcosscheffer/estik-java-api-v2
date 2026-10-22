package com.marcos.estik.stockbalance.adapter.out.departament;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.marcos.estik.departament.application.ports.in.ExistsDepartamentByIdPort;
import com.marcos.estik.stockbalance.application.ports.out.LoadStockBalanceExistsDepartamentByIdPort;

import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class LoadStockBalanceExistsDepartamentByIdAdpter implements LoadStockBalanceExistsDepartamentByIdPort {
    
    private final ExistsDepartamentByIdPort existsDepartamentByIdPort;

    @Override
    public Boolean existsById(UUID id) {
        return existsDepartamentByIdPort.execute(id);
    }
    
}
