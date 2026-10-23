package com.marcos.estik.departament.adapter.out.facility;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.marcos.estik.departament.application.ports.out.LoadDepartamentFindFacilityByIdPort;
import com.marcos.estik.departament.application.view.DepartamentFacilityInfo;
import com.marcos.estik.facility.application.ports.in.FindFacilityByIdPort;

import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class LoadDepartamentFindFacilityByIdAdapter implements LoadDepartamentFindFacilityByIdPort {
    
    private final FindFacilityByIdPort findFacilityByIdPort;
    private final DepartamentFacilityMapper departamentFacilityMapper;
    
    @Override
    public DepartamentFacilityInfo execute(UUID id) {
        return departamentFacilityMapper.toFacilityInfo(findFacilityByIdPort.execute(id));
    }
}
