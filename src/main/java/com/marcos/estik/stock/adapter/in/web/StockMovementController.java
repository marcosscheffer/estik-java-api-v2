package com.marcos.estik.stock.adapter.in.web;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.application.pagination.PageResult;
import com.marcos.estik.stock.adapter.in.web.mapper.StockMovementWebMapper;
import com.marcos.estik.stock.adapter.in.web.response.StockMovementResponse;
import com.marcos.estik.stock.adapter.in.web.response.StockMovementSummaryResponse;
import com.marcos.estik.stock.application.command.SearchStockMovementCommand;
import com.marcos.estik.stock.application.ports.in.AdjustStockMovementPort;
import com.marcos.estik.stock.application.ports.in.EntryStockMovementPort;
import com.marcos.estik.stock.application.ports.in.ExitStockMovementPort;
import com.marcos.estik.stock.application.ports.in.FindStockMovementByIdPort;
import com.marcos.estik.stock.application.ports.in.SearchStockMovementPort;
import com.marcos.estik.stock.application.ports.in.TransferStockMovementPort;
import com.marcos.estik.stock.domain.StockMovement;
import com.marcos.estik.stock.domain.TypeMovementEnum;

import io.micrometer.core.ipc.http.HttpSender.Response;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/stock/movements")
@RequiredArgsConstructor 
public class StockMovementController {

    private final AdjustStockMovementPort adjustStockMovementPort;
    private final EntryStockMovementPort entryStockMovementPort;
    private final ExitStockMovementPort exitStockMovementPort;
    private final SearchStockMovementPort searchStockMovementPort;
    private final TransferStockMovementPort transferStockMovementPort;
    private final FindStockMovementByIdPort findStockMovementByIdPort;
    private final StockMovementWebMapper stockMovementWebMapper;

    @GetMapping
    public ResponseEntity<PageResult<StockMovementSummaryResponse>> getStockMovements(
        @RequestParam(required = false) UUID facilityId,
        @RequestParam(required = false) UUID departamentId,
        @RequestParam(required = false) TypeMovementEnum type,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "20") int size
    ) {
        PageResult<StockMovement> stockMovements = searchStockMovementPort.execute(
            new SearchStockMovementCommand(
                facilityId,
                departamentId,
                type,
                new PageQuery(page, size)
            )   
        );

        return ResponseEntity.ok(stockMovements.map(stockMovementWebMapper::toSummaryResponse));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StockMovementResponse> getStockMovement(
        @PathVariable UUID id
    ) {
        StockMovement stockMovement = findStockMovementByIdPort.execute(id);
        return ResponseEntity.ok(stockMovementWebMapper.toResponse(stockMovement));
    }
    
}
