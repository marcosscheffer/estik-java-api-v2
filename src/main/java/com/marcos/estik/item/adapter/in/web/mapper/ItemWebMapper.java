package com.marcos.estik.item.adapter.in.web.mapper;

import org.mapstruct.Mapper;

import com.marcos.estik.item.adapter.in.web.request.CreateItemRequest;
import com.marcos.estik.item.adapter.in.web.request.UpdateItemRequest;
import com.marcos.estik.item.adapter.in.web.response.ItemResponse;
import com.marcos.estik.item.adapter.in.web.response.ItemSummaryResponse;
import com.marcos.estik.item.application.command.CreateItemCommand;
import com.marcos.estik.item.application.command.UpdateItemCommand;
import com.marcos.estik.item.domain.Item;

@Mapper(componentModel = "spring")
public interface ItemWebMapper {
    
    ItemResponse toResponse(Item item);

    ItemSummaryResponse toSummaryResponse(Item item);

    CreateItemCommand toCreateItemCommand(CreateItemRequest request);

    UpdateItemCommand toUpdateItemCommand(UpdateItemRequest request);

}
