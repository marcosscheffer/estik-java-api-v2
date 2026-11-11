package com.marcos.estik.specialization.adapter.in.web.mapper;

import org.mapstruct.Mapper;

import com.marcos.estik.specialization.adapter.in.web.response.SpecializationResponse;
import com.marcos.estik.specialization.domain.Specialization;

@Mapper(componentModel = "spring")
public interface SpecializationWebMapper {

    SpecializationResponse toResponse(Specialization specialization);
    
}
