package com.marcos.estik.facility.adapter.in.web.mapper;

import org.mapstruct.Mapper;

import com.marcos.estik.facility.adapter.in.web.request.CreateFacilityRequest;
import com.marcos.estik.facility.adapter.in.web.request.UpdateFacilityRequest;
import com.marcos.estik.facility.adapter.in.web.response.FacilityResponse;
import com.marcos.estik.facility.adapter.in.web.response.FacilitySummaryResponse;
import com.marcos.estik.facility.adapter.in.web.response.FacilityUsersResponse;
import com.marcos.estik.facility.application.command.CreateFacilityCommand;
import com.marcos.estik.facility.application.command.UpdateFacilityCommand;
import com.marcos.estik.facility.domain.Facility;
import com.marcos.estik.facility.domain.FacilityUser;

@Mapper(componentModel = "spring")
public interface FacilityWebMapper {
    CreateFacilityCommand toCreateFacilityCommand(CreateFacilityRequest request);

    FacilitySummaryResponse toSummaryResponse(Facility facility);

    FacilityResponse toResponse(Facility facility);

    FacilityUsersResponse toUserResponse(FacilityUser user);

    UpdateFacilityCommand toUpdateFacilityCommand(UpdateFacilityRequest request);

}
