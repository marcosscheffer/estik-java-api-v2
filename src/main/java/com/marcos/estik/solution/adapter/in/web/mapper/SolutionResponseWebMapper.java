package com.marcos.estik.solution.adapter.in.web.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.marcos.estik.solution.adapter.in.web.response.SolutionResponseResponse;
import com.marcos.estik.solution.adapter.in.web.response.SolutionResponseSummaryResponse;
import com.marcos.estik.solution.application.view.SolutionResponseView;

@Mapper(componentModel = "spring")
public interface SolutionResponseWebMapper {
    
    @Mapping(target = ".", source = "solutionResponse")
    SolutionResponseResponse toResponse(SolutionResponseView solutionResponse);

    @Mapping(target = ".", source = "solutionResponse")
    SolutionResponseSummaryResponse toSummaryResponse(SolutionResponseView solutionResponse);

}
