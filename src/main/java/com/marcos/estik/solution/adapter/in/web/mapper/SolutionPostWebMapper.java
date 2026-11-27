package com.marcos.estik.solution.adapter.in.web.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.marcos.estik.solution.adapter.in.web.response.SolutionPostResponse;
import com.marcos.estik.solution.adapter.in.web.response.SolutionPostSummaryResponse;
import com.marcos.estik.solution.application.view.SolutionPostView;

@Mapper(componentModel = "spring")
public interface SolutionPostWebMapper {

    @Mapping(target = ".", source = "solutionPost")
    SolutionPostResponse toResponse(SolutionPostView solutionPost);

    @Mapping(target = ".", source = "solutionPost")
    SolutionPostSummaryResponse toSummaryResponse(SolutionPostView solutionPost);
    
}
