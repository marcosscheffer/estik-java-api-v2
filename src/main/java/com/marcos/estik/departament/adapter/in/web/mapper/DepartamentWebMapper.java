package com.marcos.estik.departament.adapter.in.web.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.marcos.estik.departament.adapter.in.web.request.CreateDepartamentRequest;
import com.marcos.estik.departament.adapter.in.web.request.UpdateDepartamentRequest;
import com.marcos.estik.departament.adapter.in.web.response.DepartamentResponse;
import com.marcos.estik.departament.adapter.in.web.response.DepartamentSummaryResponse;
import com.marcos.estik.departament.application.command.CreateDepartamentCommand;
import com.marcos.estik.departament.application.command.UpdateDepartamentCommand;
import com.marcos.estik.departament.application.view.DepartamentView;

@Mapper(componentModel = "spring")
public interface DepartamentWebMapper {
    
    @Mapping(target = ".", source = "departament")
    DepartamentResponse toResponse(DepartamentView departament);

    @Mapping(target = ".", source = "departament")
    DepartamentSummaryResponse toSummaryResponse(DepartamentView departament);

    CreateDepartamentCommand toCreateDepartamentCommand(CreateDepartamentRequest request);

    UpdateDepartamentCommand toUpdateDepartamentCommand(UpdateDepartamentRequest request);
    
}
