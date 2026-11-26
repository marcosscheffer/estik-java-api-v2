package com.marcos.estik.purchase.adapter.in.web;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.marcos.estik.purchase.adapter.in.web.mapper.PurchaseWebMapper;
import com.marcos.estik.purchase.adapter.in.web.response.PurchaseResponse;
import com.marcos.estik.purchase.adapter.in.web.response.PurchaseSummaryResponse;
import com.marcos.estik.purchase.application.port.in.FindPurchaseByIdPort;
import com.marcos.estik.purchase.application.port.in.SearchPurchasePort;
import com.marcos.estik.purchase.application.view.PurchaseView;
import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.application.pagination.PageResult;

import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping("/purchases")
@RequiredArgsConstructor 
public class PurchaseController {

    private final FindPurchaseByIdPort findPurchaseByIdPort;
    private final SearchPurchasePort searchPurchasePort;
    private final PurchaseWebMapper purchaseWebMapper;

    @GetMapping("/{id}")
    public ResponseEntity<PurchaseResponse> getPurchase(
        @PathVariable UUID id
    ) {
        PurchaseView purchase = findPurchaseByIdPort.execute(id);
        return ResponseEntity.ok(purchaseWebMapper.toResponse(purchase));
    }

    @GetMapping
    public ResponseEntity<PageResult<PurchaseSummaryResponse>> searchPurchases(
        @RequestParam(defaultValue = "") String code,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "20") int size
    ) {
        PageResult<PurchaseView> purchases = searchPurchasePort.execute(code, new PageQuery(page, size));
        return ResponseEntity.ok(
            purchases.map(purchaseWebMapper::toSummaryResponse)
        );
    }
}
