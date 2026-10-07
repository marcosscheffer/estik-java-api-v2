package com.marcos.estik.facility.adapter.in.web.mapper;

import org.mapstruct.Mapper;

import com.marcos.estik.facility.adapter.in.web.request.CreateFacilityRequest;
import com.marcos.estik.facility.adapter.in.web.response.FacilitySummaryResponse;
import com.marcos.estik.facility.application.command.CreateFacilityCommand;
import com.marcos.estik.facility.domain.Facility;

@Mapper(componentModel = "spring")
public interface FacilityWebMapper {
    CreateFacilityCommand toCreateFacilityCommand(CreateFacilityRequest request);

    FacilitySummaryResponse toResponse(Facility facility);
}
