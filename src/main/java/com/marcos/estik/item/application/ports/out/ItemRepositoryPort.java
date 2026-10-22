package com.marcos.estik.item.application.ports.out;

import java.util.UUID;

import com.marcos.estik.item.domain.Item;
import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.application.pagination.PageResult;

public interface ItemRepositoryPort {

    Item save(Item item);

    Item findById(UUID id);

    Boolean existsById(UUID id);

    PageResult<Item> SearchItem(
        String name,
        String code,
        UUID categoryId,
        PageQuery page
    );

}
