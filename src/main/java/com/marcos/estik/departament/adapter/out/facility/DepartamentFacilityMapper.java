package com.marcos.estik.departament.adapter.out.facility;

import org.mapstruct.Mapper;

import com.marcos.estik.departament.application.view.DepartamentFacilityInfo;
import com.marcos.estik.facility.domain.Facility;

@Mapper(componentModel = "spring")
public interface DepartamentFacilityMapper {
    
    DepartamentFacilityInfo toFacilityInfo(Facility facility);

}
