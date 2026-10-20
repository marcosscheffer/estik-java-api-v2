package com.marcos.estik.item.adapter.out.persistence;

import org.mapstruct.Mapper;

import com.marcos.estik.item.domain.Item;

@Mapper(componentModel = "spring")
public interface ItemPersistenceMapper {
    ItemEntity toEntity(Item item);

    Item toDomain(ItemEntity item);
    
}
