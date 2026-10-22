package com.marcos.estik.item.adapter.out.persistence;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.marcos.estik.item.application.ports.out.ItemRepositoryPort;
import com.marcos.estik.item.domain.Item;
import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.application.pagination.PageResult;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class ItemRepositoryAdapter implements ItemRepositoryPort {
    
    private final ItemPersistenceMapper itemPersistenceMapper;
    private final JpaItemRepository jpaItemRepository;
    
    @Override
    public Item save(Item item) {
        ItemEntity itemEntity = itemPersistenceMapper.toEntity(item);
        ItemEntity ItemSaved = jpaItemRepository.save(itemEntity);
        return itemPersistenceMapper.toDomain(ItemSaved);
    }

    @Override
    public Item findById(UUID id) {
        ItemEntity item = jpaItemRepository.findById(id)
            .orElseThrow(EntityNotFoundException::new);
        return itemPersistenceMapper.toDomain(item);
    }

    @Override
    public PageResult<Item> SearchItem(String name, String code, UUID categoryId, PageQuery page) {
        Specification<ItemEntity> specs = Specification.allOf(
            ItemSpecification.nameContain(name),
            ItemSpecification.skuContain(code),
            ItemSpecification.categoryIs(categoryId)
        );

        Page<ItemEntity> items = jpaItemRepository.findAll(
            specs, 
            PageRequest.of(page.page(), page.size())
        );

        return new PageResult<Item>(
            items.getContent().stream().map(itemPersistenceMapper::toDomain).toList(),
            items.getNumber(),
            items.getSize(),
            items.getTotalElements(),
            items.getTotalPages()
        );
    }

    @Override
    public Boolean existsById(UUID id) {
        return jpaItemRepository.existsById(id);
    }
}
