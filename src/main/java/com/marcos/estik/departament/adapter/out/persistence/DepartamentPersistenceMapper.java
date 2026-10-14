package com.marcos.estik.departament.adapter.out.persistence;

import org.mapstruct.Mapper;

import com.marcos.estik.departament.domain.Departament;


@Mapper(componentModel = "spring")
public interface DepartamentPersistenceMapper {

    DepartamentEntity toEntity(Departament departament);

    Departament toDomain(DepartamentEntity departament);
    
}
