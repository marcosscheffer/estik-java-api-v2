package com.marcos.estik.solution.adapter.out.specialization;

import org.mapstruct.Mapper;

import com.marcos.estik.solution.application.view.SolutionSpecializationInfo;
import com.marcos.estik.specialization.domain.Specialization;

@Mapper(componentModel = "spring")
public interface SolutionSpecializationMapper {

    SolutionSpecializationInfo toInfo(Specialization specialization);
    
}
