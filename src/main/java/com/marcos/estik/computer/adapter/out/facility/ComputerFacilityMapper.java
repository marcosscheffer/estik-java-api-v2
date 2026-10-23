package com.marcos.estik.computer.adapter.out.facility;

import org.mapstruct.Mapper;

import com.marcos.estik.computer.application.view.ComputerFacilityInfo;
import com.marcos.estik.facility.domain.Facility;

@Mapper(componentModel = "spring")
public interface ComputerFacilityMapper {
    
    ComputerFacilityInfo toInfo(Facility facility);

}
