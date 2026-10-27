package com.marcos.estik.computerhistory.adapter.out.computer;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.marcos.estik.computer.application.view.ComputerView;
import com.marcos.estik.computerhistory.application.view.ComputerHistoryComputerInfo;

@Mapper(componentModel = "spring")
public interface ComputerHistoryComputerMapper {

    @Mapping(target = ".", source = "computer")
    ComputerHistoryComputerInfo toComputerInfo(ComputerView computer);
    
}
