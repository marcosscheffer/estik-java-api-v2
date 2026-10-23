package com.marcos.estik.stock.adapter.out.facility;

import org.mapstruct.Mapper;

import com.marcos.estik.facility.domain.Facility;
import com.marcos.estik.stock.application.view.StockFacilityInfo;

@Mapper(componentModel = "spring")
public interface StockFacilityMapper {

    StockFacilityInfo toFacilityInfo(Facility facility);
    
}
