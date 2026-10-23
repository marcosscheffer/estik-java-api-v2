package com.marcos.estik.computer.adapter.out.departament;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.marcos.estik.computer.application.view.ComputerDepartamentInfo;
import com.marcos.estik.departament.application.view.DepartamentView;

@Mapper(componentModel = "spring")
public interface ComputerDepartamentMapper {
    
    @Mapping(target = ".", source = "departament")
    ComputerDepartamentInfo toInfo(DepartamentView departament);

}
