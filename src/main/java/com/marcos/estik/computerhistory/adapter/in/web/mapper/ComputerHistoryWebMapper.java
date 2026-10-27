package com.marcos.estik.computerhistory.adapter.in.web.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.marcos.estik.computerhistory.adapter.in.web.response.ComputerHistoryResponse;
import com.marcos.estik.computerhistory.adapter.in.web.response.ComputerHistorySummaryResponse;
import com.marcos.estik.computerhistory.application.view.ComputerHistoryView;

@Mapper(componentModel = "spring")
public interface ComputerHistoryWebMapper {
    @Mapping(target = ".", source = "computerHistory")
    ComputerHistorySummaryResponse toSummaryResponse(ComputerHistoryView computerHistory);

    @Mapping(target = ".", source = "computerHistory")
    ComputerHistoryResponse toResponse(ComputerHistoryView computerHistory);
}
