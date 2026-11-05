package com.marcos.estik.computer.adapter.out.persistence.computer;

import org.mapstruct.Mapper;

import com.marcos.estik.computer.domain.Computer;

@Mapper(componentModel = "spring")
public interface ComputerPersistenceMapper {

    ComputerEntity toEntity(Computer Computer);

    Computer toDomain(ComputerEntity computerEntity);
    
}
