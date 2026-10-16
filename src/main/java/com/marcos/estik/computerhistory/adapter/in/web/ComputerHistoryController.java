package com.marcos.estik.computerhistory.adapter.in.web;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.marcos.estik.computerhistory.adapter.in.web.mapper.ComputerHistoryWebMapper;
import com.marcos.estik.computerhistory.adapter.in.web.response.ComputerHistoryResponse;
import com.marcos.estik.computerhistory.adapter.in.web.response.ComputerHistorySummaryResponse;
import com.marcos.estik.computerhistory.application.command.SearchComputerHistoryCommand;
import com.marcos.estik.computerhistory.application.ports.in.FindComputerHistoryByIdPort;
import com.marcos.estik.computerhistory.application.ports.in.SearchComputerHistoryPort;
import com.marcos.estik.computerhistory.domain.ComputerHistory;
import com.marcos.estik.computerhistory.domain.DirectionEnum;
import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.application.pagination.PageResult;

import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping("/histories")
@RequiredArgsConstructor 
public class ComputerHistoryController {

    private final ComputerHistoryWebMapper computerHistoryWebMapper;
    private final SearchComputerHistoryPort searchComputerHistoryPort;
    private final FindComputerHistoryByIdPort findComputerHistoryByIdPort;

    @GetMapping
    public ResponseEntity<PageResult<ComputerHistorySummaryResponse>> getComputerHistories(
        @RequestParam(required = false) DirectionEnum direction,
        @RequestParam(required = false) UUID computerId,
        @RequestParam(required = false) UUID departamentId,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "20") int size
    ) {
        PageResult<ComputerHistory> computerHistories = searchComputerHistoryPort.execute(
            new SearchComputerHistoryCommand(
                direction,
                computerId,
                departamentId,
                new PageQuery(page, size)
            )
        );

        return ResponseEntity.ok(computerHistories.map(computerHistoryWebMapper::toSummaryResponse));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComputerHistoryResponse> getComputerHistory(
        @PathVariable UUID id
    ) {
        ComputerHistory computerHistory = findComputerHistoryByIdPort.execute(id);

        return ResponseEntity.ok(computerHistoryWebMapper.toResponse(computerHistory));
    }
}
