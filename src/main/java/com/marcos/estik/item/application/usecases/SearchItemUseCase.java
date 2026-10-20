package com.marcos.estik.item.application.usecases;

import org.springframework.stereotype.Service;

import com.marcos.estik.item.application.command.SearchItemCommand;
import com.marcos.estik.item.application.ports.in.SearchItemPort;
import com.marcos.estik.item.application.ports.out.ItemRepositoryPort;
import com.marcos.estik.item.domain.Item;
import com.marcos.estik.shared.application.pagination.PageResult;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class SearchItemUseCase implements SearchItemPort {
    
    private final ItemRepositoryPort itemRepositoryPort;
    
    @Override
    public PageResult<Item> execute(SearchItemCommand command) {
        return itemRepositoryPort.SearchItem(
            command.name(), 
            command.code(), 
            command.page()
        );
    }
    
}
