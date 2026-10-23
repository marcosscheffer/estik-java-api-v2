package com.marcos.estik.item.adapter.in.web.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.marcos.estik.item.adapter.in.web.request.CreateItemRequest;
import com.marcos.estik.item.adapter.in.web.request.UpdateItemRequest;
import com.marcos.estik.item.adapter.in.web.response.ItemResponse;
import com.marcos.estik.item.adapter.in.web.response.ItemSummaryResponse;
import com.marcos.estik.item.application.command.CreateItemCommand;
import com.marcos.estik.item.application.command.UpdateItemCommand;
import com.marcos.estik.item.application.view.ItemView;

@Mapper(componentModel = "spring")
public interface ItemWebMapper {
    
    @Mapping(target = ".", source = "item")
    ItemResponse toResponse(ItemView item);

    @Mapping(target = ".", source = "item")
    ItemSummaryResponse toSummaryResponse(ItemView item);

    CreateItemCommand toCreateItemCommand(CreateItemRequest request);

    UpdateItemCommand toUpdateItemCommand(UpdateItemRequest request);

}
