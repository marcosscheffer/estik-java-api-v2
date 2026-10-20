package com.marcos.estik.item.application.ports.in;

import com.marcos.estik.item.application.command.SearchItemCommand;
import com.marcos.estik.item.domain.Item;
import com.marcos.estik.shared.application.pagination.PageResult;

public interface SearchItemPort {
    PageResult<Item> execute(SearchItemCommand command);
}
