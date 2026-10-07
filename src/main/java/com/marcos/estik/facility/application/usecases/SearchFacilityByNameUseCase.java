package com.marcos.estik.facility.application.usecases;

import org.springframework.stereotype.Service;

import com.marcos.estik.facility.application.ports.in.SearchFacilityByNamePort;
import com.marcos.estik.facility.application.ports.out.FacilityRepositoryPort;
import com.marcos.estik.facility.domain.Facility;
import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.application.pagination.PageResult;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class SearchFacilityByNameUseCase implements SearchFacilityByNamePort{

    private final FacilityRepositoryPort facilityRepositoryPort;

    @Override
    public PageResult<Facility> execute(String name, PageQuery page) {
        return facilityRepositoryPort.SearchFacilityByName(name, page);
    }
    
}
