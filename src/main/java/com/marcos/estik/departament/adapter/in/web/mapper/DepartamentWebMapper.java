package com.marcos.estik.departament.adapter.in.web.mapper;

import org.mapstruct.Mapper;

import com.marcos.estik.departament.adapter.in.web.request.CreateDepartamentRequest;
import com.marcos.estik.departament.adapter.in.web.request.UpdateDepartamentRequest;
import com.marcos.estik.departament.adapter.in.web.response.DepartamentResponse;
import com.marcos.estik.departament.adapter.in.web.response.DepartamentSummaryResponse;
import com.marcos.estik.departament.application.command.CreateDepartamentCommand;
import com.marcos.estik.departament.application.command.UpdateDepartamentCommand;
import com.marcos.estik.departament.domain.Departament;

@Mapper(componentModel = "spring")
public interface DepartamentWebMapper {
    DepartamentResponse toResponse(Departament departament);

    DepartamentSummaryResponse toSummaryResponse(Departament departament);

    CreateDepartamentCommand toCreateDepartamentCommand(CreateDepartamentRequest request);

    UpdateDepartamentCommand toUpdateDepartamentCommand(UpdateDepartamentRequest request);
    
}
