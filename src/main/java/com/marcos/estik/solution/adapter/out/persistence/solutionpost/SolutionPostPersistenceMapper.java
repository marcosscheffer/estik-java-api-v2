package com.marcos.estik.solution.adapter.out.persistence.solutionpost;

import org.mapstruct.Mapper;

import com.marcos.estik.solution.domain.SolutionPost;

@Mapper(componentModel = "spring")
public interface SolutionPostPersistenceMapper {

    SolutionPostEntity toEntity(SolutionPost solutionPost);

    SolutionPost toDomain(SolutionPostEntity solutionPost);
    
}
