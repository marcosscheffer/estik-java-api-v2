package com.marcos.estik.category.adapter.out.persistence;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.marcos.estik.category.application.ports.out.CategoryRepositoryPort;
import com.marcos.estik.category.domain.Category;
import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.application.pagination.PageResult;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class CategoryRepositoryAdapter implements CategoryRepositoryPort {

    private final JpaCategoryRepository jpaCategoryRepository;
    private final CategoryPersistenceMapper categoryPersistenceMapper;
    
    @Override
    public Category save(Category category) {
        CategoryEntity categoryEntity = categoryPersistenceMapper.toEntity(category);
        CategoryEntity categorySaved = jpaCategoryRepository.save(categoryEntity);
        return categoryPersistenceMapper.toDomain(categorySaved);
    }

    @Override
    public Category findById(UUID categoryId) {
        CategoryEntity categoryEntity = jpaCategoryRepository.findById(categoryId)
            .orElseThrow(EntityNotFoundException::new);
        return categoryPersistenceMapper.toDomain(categoryEntity);
    }

    @Override
    public PageResult<Category> searchCategory(String name, PageQuery page) {
        Specification<CategoryEntity> spec = Specification.allOf(
            CategorySpecification.nameContain(name)
        );

        Page<CategoryEntity> categories = jpaCategoryRepository.findAll(
            spec, 
            PageRequest.of(page.page(), page.size())
        );

        return new PageResult<Category>(
            categories.getContent().stream().map(categoryPersistenceMapper::toDomain).toList(),
            categories.getNumber(),
            categories.getSize(),
            categories.getTotalElements(),
            categories.getTotalPages()
        );
    }

    @Override
    public Boolean existsById(UUID categoryId) {
        return jpaCategoryRepository.existsById(categoryId);
    }
}
