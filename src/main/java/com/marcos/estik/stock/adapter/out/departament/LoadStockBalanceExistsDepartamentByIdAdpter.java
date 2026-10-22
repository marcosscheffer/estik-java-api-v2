package com.marcos.estik.stock.adapter.out.departament;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.marcos.estik.departament.application.ports.in.ExistsDepartamentByIdPort;
import com.marcos.estik.stock.application.ports.out.LoadStockExistsDepartamentByIdPort;

import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class LoadStockBalanceExistsDepartamentByIdAdpter implements LoadStockExistsDepartamentByIdPort {
    
    private final ExistsDepartamentByIdPort existsDepartamentByIdPort;

    @Override
    public Boolean existsById(UUID id) {
        return existsDepartamentByIdPort.execute(id);
    }
    
}
