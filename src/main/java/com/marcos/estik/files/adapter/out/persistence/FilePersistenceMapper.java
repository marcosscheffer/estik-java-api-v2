package com.marcos.estik.files.adapter.out.persistence;

import org.mapstruct.Mapper;

import com.marcos.estik.files.domain.File;

@Mapper(componentModel = "spring")
public interface FilePersistenceMapper {

    FileEntity toEntity(File attachment);

    File toDomain(FileEntity attachment);
    
}
