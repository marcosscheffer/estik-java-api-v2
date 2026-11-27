package com.marcos.estik.solution.adapter.out.file;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.marcos.estik.files.domain.File;
import com.marcos.estik.solution.application.view.SolutionFileInfo;

@Mapper(componentModel = "spring")
public interface SolutionFileMapper {

    @Mapping(target = "fileUrl", source = "signedUrl")
    SolutionFileInfo toInfo(File file, String signedUrl);
    
}
