package com.marcos.estik.computer.adapter.out.persistence.computerhistory;

import org.mapstruct.Mapper;

import com.marcos.estik.computer.domain.ComputerHistory;

@Mapper(componentModel = "spring")
public interface ComputerHistoryPersistenceMapper {

    ComputerHistoryEntity toEntity(ComputerHistory computerHistory);

    ComputerHistory toDomain(ComputerHistoryEntity computerHistory);

}
