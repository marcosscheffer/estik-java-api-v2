package com.marcos.estik.item.adapter.in.web;

import java.net.URI;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.marcos.estik.item.adapter.in.web.mapper.ItemWebMapper;
import com.marcos.estik.item.adapter.in.web.request.CreateItemRequest;
import com.marcos.estik.item.adapter.in.web.request.UpdateItemRequest;
import com.marcos.estik.item.adapter.in.web.response.ItemResponse;
import com.marcos.estik.item.adapter.in.web.response.ItemSummaryResponse;
import com.marcos.estik.item.application.command.SearchItemCommand;
import com.marcos.estik.item.application.ports.in.CreateItemPort;
import com.marcos.estik.item.application.ports.in.FindItemByIdPort;
import com.marcos.estik.item.application.ports.in.SearchItemPort;
import com.marcos.estik.item.application.ports.in.UpdateItemPort;
import com.marcos.estik.item.application.view.ItemView;
import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.application.pagination.PageResult;

import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping("/items")
@RequiredArgsConstructor 
public class ItemController {

    private final CreateItemPort createItemPort;
    private final FindItemByIdPort findItemByIdPort;
    private final SearchItemPort searchItemPort;
    private final UpdateItemPort updateItemPort;
    private final ItemWebMapper itemWebMapper;

    @GetMapping("/{id}")
    public ResponseEntity<ItemResponse> getItem(
        @PathVariable UUID id
    ) {
        ItemView item = findItemByIdPort.execute(id);
        return ResponseEntity.ok(itemWebMapper.toResponse(item));
    }

    @GetMapping
    public ResponseEntity<PageResult<ItemSummaryResponse>> getItems(
        @RequestParam(defaultValue = "") String name,
        @RequestParam(defaultValue = "") String code,
        @RequestParam(required = false) UUID categoryId,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "20") int size
    ) {
        PageResult<ItemView> items = searchItemPort.execute(
            new SearchItemCommand(
                name,
                code,
                categoryId,
                new PageQuery(page, size)
            )
        );

        return ResponseEntity.ok(
            items.map(itemWebMapper::toSummaryResponse)
        );
    }

    @PostMapping
    public ResponseEntity<ItemResponse> createItem(
        @RequestBody CreateItemRequest request,
        UriComponentsBuilder uriBuilder
    ) {
        ItemView item = createItemPort.execute(itemWebMapper.toCreateItemCommand(request));
        URI uri = uriBuilder
            .path("items/{id}")
            .buildAndExpand(item.item().getId())
            .toUri();
        return ResponseEntity.created(uri).body(itemWebMapper.toResponse(item));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemResponse> updateItem(
        @PathVariable UUID id,
        @RequestBody UpdateItemRequest request
    ) {
        ItemView item = updateItemPort.execute(
            id,
            itemWebMapper.toUpdateItemCommand(request)
         );

         return ResponseEntity.ok(itemWebMapper.toResponse(item));
    }
}
