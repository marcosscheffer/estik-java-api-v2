package com.marcos.estik.category.adapter.in.web;

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

import com.marcos.estik.category.adapter.in.web.mapper.CategoryWebMapper;
import com.marcos.estik.category.adapter.in.web.request.CreateCategoryRequest;
import com.marcos.estik.category.adapter.in.web.request.UpdateCategoryRequest;
import com.marcos.estik.category.adapter.in.web.response.CategoryResponse;
import com.marcos.estik.category.adapter.in.web.response.CategorySummaryResponse;
import com.marcos.estik.category.application.command.SearchCategoryCommand;
import com.marcos.estik.category.application.ports.in.CreateCategoryPort;
import com.marcos.estik.category.application.ports.in.FindCategoryByIdPort;
import com.marcos.estik.category.application.ports.in.SearchCategoryPort;
import com.marcos.estik.category.application.ports.in.UpdateCategoryPort;
import com.marcos.estik.category.domain.Category;
import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.application.pagination.PageResult;

import lombok.RequiredArgsConstructor;


@RestController 
@RequestMapping("/categories")
@RequiredArgsConstructor 
public class CategoryController {
    
    private final CreateCategoryPort createCategoryPort;
    private final FindCategoryByIdPort findCategoryByIdPort;
    private final SearchCategoryPort searchCategoryPort;
    private final UpdateCategoryPort updateCategoryPort;
    private final CategoryWebMapper categoryWebMapper;

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getCategory(
        @PathVariable UUID id
    ) {
        Category category = findCategoryByIdPort.execute(id);
        return ResponseEntity.ok(categoryWebMapper.toResponse(category));
    }

    @GetMapping
    public ResponseEntity<PageResult<CategorySummaryResponse>> getCategories(
        @RequestParam(defaultValue = "") String name,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "20") int size
    ) {
        PageResult<Category> categories = searchCategoryPort.execute(
            new SearchCategoryCommand(
                name,
                new PageQuery(page, size)
            )
        );

        return ResponseEntity.ok(categories.map(categoryWebMapper::toSummaryResponse));
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> createCategory(
        @RequestBody CreateCategoryRequest request,
        UriComponentsBuilder uriBuilder
    ) {
        Category category = createCategoryPort.execute(categoryWebMapper.toCreateCategoryCommand(request));
        URI uri = uriBuilder
            .path("/categories/{id}")
            .buildAndExpand(category.getId())
            .toUri();
        return ResponseEntity.created(uri).body(categoryWebMapper.toResponse(category));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> updateCategory(
        @PathVariable UUID id,
        @RequestBody UpdateCategoryRequest request
    ) {
        Category category = updateCategoryPort.execute(id, categoryWebMapper.toUpdateCategoryCommand(request));
        return ResponseEntity.ok(categoryWebMapper.toResponse(category));
    }
}
