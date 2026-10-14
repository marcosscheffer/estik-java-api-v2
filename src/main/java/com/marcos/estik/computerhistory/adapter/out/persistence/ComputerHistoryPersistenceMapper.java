package com.marcos.estik.computerhistory.adapter.out.persistence;

import org.mapstruct.Mapper;

import com.marcos.estik.computerhistory.domain.ComputerHistory;

@Mapper(componentModel = "spring")
public interface ComputerHistoryPersistenceMapper {

    ComputerHistoryEntity toEntity(ComputerHistory computerHistory);

    ComputerHistory toDomain(ComputerHistoryEntity computerHistory);

}
