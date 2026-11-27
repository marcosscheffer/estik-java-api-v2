package com.marcos.estik.solution.adapter.out.persistence.solutionresponse;

import org.mapstruct.Mapper;

import com.marcos.estik.solution.domain.SolutionResponse;

@Mapper(componentModel = "spring")
public interface SolutionResponsePersistenceMapper {

    SolutionResponseEntity toEntity(SolutionResponse solutionResponse);
    
    SolutionResponse toDomain(SolutionResponseEntity solutionResponse);
    
}
