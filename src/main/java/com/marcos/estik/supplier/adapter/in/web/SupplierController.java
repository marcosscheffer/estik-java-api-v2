package com.marcos.estik.supplier.adapter.in.web;

import java.net.URI;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.application.pagination.PageResult;
import com.marcos.estik.supplier.adapter.in.web.mapper.SupplierWebMapper;
import com.marcos.estik.supplier.adapter.in.web.request.CreateSupplierRequest;
import com.marcos.estik.supplier.adapter.in.web.response.SupplierResponse;
import com.marcos.estik.supplier.application.port.in.CreateSupplierPort;
import com.marcos.estik.supplier.application.port.in.FindSupplierByIdPort;
import com.marcos.estik.supplier.application.port.in.SearchSupplierPort;
import com.marcos.estik.supplier.domain.Supplier;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping("/suppliers")
@RequiredArgsConstructor 
public class SupplierController {

    private final CreateSupplierPort createSupplierPort;
    private final SearchSupplierPort searchSupplierPort;
    private final FindSupplierByIdPort findSupplierByIdPort;
    private final SupplierWebMapper supplierWebMapper;
    
    @PostMapping
    public ResponseEntity<SupplierResponse> createSupplier(
        @RequestBody @Valid CreateSupplierRequest request,
        UriComponentsBuilder uriBuilder
    ) {
        Supplier supplier = createSupplierPort.execute(request.name());
        URI uri = uriBuilder
            .path("/supplier/{id}")
            .buildAndExpand(supplier.getId())
            .toUri();
        return ResponseEntity.created(uri).body(supplierWebMapper.toResponse(supplier)); 
    }

    @GetMapping
    public ResponseEntity<PageResult<SupplierResponse>> searchSupplier(
        @RequestParam(defaultValue = "") String name,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "20") int size
    ) {
        PageResult<Supplier> suppliers = searchSupplierPort.execute(name, new PageQuery(page, size));
        return ResponseEntity.ok(
            suppliers.map(
                supplierWebMapper::toResponse
            )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupplierResponse> findById(
        @PathVariable UUID id
    ) {
        Supplier supplier = findSupplierByIdPort.execute(id);
        return ResponseEntity.ok(
            supplierWebMapper.toResponse(supplier)
        );
    }

}
