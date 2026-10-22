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
import com.marcos.estik.stock.adapter.in.web.mapper.StockBalanceWebMapper;
import com.marcos.estik.stock.adapter.in.web.response.StockBalanceResponse;
import com.marcos.estik.stock.adapter.in.web.response.StockBalanceSummaryResponse;
import com.marcos.estik.stock.application.command.SearchStockBalanceCommand;
import com.marcos.estik.stock.application.ports.in.FindStockBalanceByIdPort;
import com.marcos.estik.stock.application.ports.in.SearchStockBalancePort;
import com.marcos.estik.stock.domain.StockBalance;

import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping("/stock/balances")
@RequiredArgsConstructor 
public class StockBalanceController {
    
    private final FindStockBalanceByIdPort findStockBalanceByIdPort;
    private final SearchStockBalancePort searchStockBalancePort;
    private final StockBalanceWebMapper stockBalanceWebMapper;

    @GetMapping
    public ResponseEntity<PageResult<StockBalanceSummaryResponse>> getAllStockBalance(
        @RequestParam(required = true) UUID facilityId,
        @RequestParam(required = false) UUID departamentId,
        @RequestParam(required = false) UUID itemId,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "20") int size
    ) {
        PageResult<StockBalance> stockBalancePage = searchStockBalancePort.execute(
            new SearchStockBalanceCommand(
                facilityId,
                departamentId,
                itemId,
                new PageQuery(page, size)
            )   
        );
        return ResponseEntity.ok(stockBalancePage.map(stockBalanceWebMapper::toSummaryResponse));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StockBalanceResponse> getStockBalance(
        @PathVariable UUID id
    ) {
        StockBalance stockBalance = findStockBalanceByIdPort.execute(id);
        return ResponseEntity.ok(stockBalanceWebMapper.toResponse(stockBalance));
    }
}
