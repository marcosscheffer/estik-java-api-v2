package com.marcos.estik.computerhistory.adapter.in.web.mapper;

import org.mapstruct.Mapper;

import com.marcos.estik.computerhistory.adapter.in.web.request.CreateComputerHistoryRequest;
import com.marcos.estik.computerhistory.adapter.in.web.response.ComputerHistoryResponse;
import com.marcos.estik.computerhistory.adapter.in.web.response.ComputerHistorySummaryResponse;
import com.marcos.estik.computerhistory.application.command.CreateComputerHistoryCommand;
import com.marcos.estik.computerhistory.domain.ComputerHistory;

@Mapper(componentModel = "spring")
public interface ComputerHistoryWebMapper {
    ComputerHistorySummaryResponse toSummaryResponse(ComputerHistory computerHistory);

    ComputerHistoryResponse toResponse(ComputerHistory computerHistory);

    CreateComputerHistoryCommand toCreateComputerHistoryCommand(CreateComputerHistoryRequest request);
}
