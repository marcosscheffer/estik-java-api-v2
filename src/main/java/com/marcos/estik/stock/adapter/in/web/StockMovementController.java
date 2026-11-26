package com.marcos.estik.stock.adapter.in.web;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.List;
import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.application.pagination.PageResult;
import com.marcos.estik.shared.domain.file.FileUpload;
import com.marcos.estik.stock.adapter.in.web.mapper.StockMovementWebMapper;
import com.marcos.estik.stock.adapter.in.web.request.MovementPurchaseRequest;
import com.marcos.estik.stock.adapter.in.web.request.MovementRequest;
import com.marcos.estik.stock.adapter.in.web.request.TransferRequest;
import com.marcos.estik.stock.adapter.in.web.response.StockMovementResponse;
import com.marcos.estik.stock.adapter.in.web.response.StockMovementSummaryResponse;
import com.marcos.estik.stock.application.command.EntryPurchaseCommand;
import com.marcos.estik.stock.application.command.SearchStockMovementCommand;
import com.marcos.estik.stock.application.ports.in.AdjustStockPort;
import com.marcos.estik.stock.application.ports.in.EntryPurchasePort;
import com.marcos.estik.stock.application.ports.in.EntryStockPort;
import com.marcos.estik.stock.application.ports.in.ExitStockPort;
import com.marcos.estik.stock.application.ports.in.FindStockMovementByIdPort;
import com.marcos.estik.stock.application.ports.in.SearchStockMovementPort;
import com.marcos.estik.stock.application.ports.in.TransferStockPort;
import com.marcos.estik.stock.application.view.StockMovementView;
import com.marcos.estik.stock.domain.TypeMovementEnum;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/stock/movements")
@RequiredArgsConstructor 
public class StockMovementController {

    private final SearchStockMovementPort searchStockMovementPort;
    private final FindStockMovementByIdPort findStockMovementByIdPort;
    private final EntryStockPort entryStockPort;
    private final ExitStockPort exitStockPort;
    private final AdjustStockPort adjustStockPort;
    private final TransferStockPort transferStockPort;
    private final StockMovementWebMapper stockMovementWebMapper;
    private final EntryPurchasePort entryPurchasePort;

    @GetMapping
    public ResponseEntity<PageResult<StockMovementSummaryResponse>> getStockMovements(
        @RequestParam(required = false) UUID facilityId,
        @RequestParam(required = false) UUID departamentId,
        @RequestParam(required = false) TypeMovementEnum type,
        @RequestParam(required = false) UUID purchaseId,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "20") int size
    ) {
        PageResult<StockMovementView> stockMovements = searchStockMovementPort.execute(
            new SearchStockMovementCommand(
                facilityId,
                departamentId,
                purchaseId,
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
        StockMovementView stockMovement = findStockMovementByIdPort.execute(id);
        return ResponseEntity.ok(stockMovementWebMapper.toResponse(stockMovement));
    }

    @PostMapping("/entry")
    public ResponseEntity<StockMovementResponse> entryMovement(
        @RequestBody @Valid MovementRequest request
    ) {
        StockMovementView stockMovement = entryStockPort.execute(
            stockMovementWebMapper.toMovementCommand(request)
        );

        return ResponseEntity.ok(stockMovementWebMapper.toResponse(stockMovement));
    }

    @PostMapping("/exit")
    public ResponseEntity<StockMovementResponse> exitMovement(
        @RequestBody @Valid MovementRequest request
    ) {
        StockMovementView stockMovement = exitStockPort.execute(
            stockMovementWebMapper.toMovementCommand(request)
        );
        
        return ResponseEntity.ok(stockMovementWebMapper.toResponse(stockMovement));
    }

    @PostMapping("/adjust")
    public ResponseEntity<StockMovementResponse> adjustMovement(
        @RequestBody @Valid MovementRequest request
    ) {
        StockMovementView stockMovement = adjustStockPort.execute(
            stockMovementWebMapper.toMovementCommand(request)
        );

        return ResponseEntity.ok(stockMovementWebMapper.toResponse(stockMovement));
    }

    @PostMapping("/transfer")
    public ResponseEntity<StockMovementResponse> transferMovement(
        @RequestBody @Valid TransferRequest request
    ) {
        StockMovementView stockMovement = transferStockPort.execute(
            stockMovementWebMapper.toTransferCommand(request)
        );

        return ResponseEntity.ok(stockMovementWebMapper.toResponse(stockMovement));
    }

    @PostMapping(path = "/entry/purchase", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<StockMovementResponse>> entryPurchase(
        @RequestPart("request") @Valid MovementPurchaseRequest request,
        @RequestPart(value = "files", required = false) MultipartFile file
    ) {
        List<StockMovementView> movements = entryPurchasePort.execute(
            new EntryPurchaseCommand(
                request.supplierId(),
                request.code(),
                toFileUpload(file),
                request.items().stream().map(stockMovementWebMapper::toMovementCommand).toList()
            )
        );

        return ResponseEntity.ok(movements.stream().map(stockMovementWebMapper::toResponse).toList());
    }


    private FileUpload toFileUpload(MultipartFile multipartFile) {
        if (multipartFile == null || multipartFile.isEmpty()) {
            return null;
        }
        
        try {
            return new FileUpload(
                multipartFile.getOriginalFilename(),
                multipartFile.getSize(),
                multipartFile.getBytes()
            );
        } catch (IOException exception) {
            throw new UncheckedIOException("Failed to read uploaded file", exception);
        }
    }
}
