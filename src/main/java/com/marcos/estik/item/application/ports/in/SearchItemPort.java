package com.marcos.estik.item.application.ports.in;

import com.marcos.estik.item.application.command.SearchItemCommand;
import com.marcos.estik.item.application.view.ItemView;
import com.marcos.estik.shared.application.pagination.PageResult;

public interface SearchItemPort {
    PageResult<ItemView> execute(SearchItemCommand command);
}
