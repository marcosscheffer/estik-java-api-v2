package com.marcos.estik.chat.adapter.out.file;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.marcos.estik.chat.application.view.ChatFileInfo;
import com.marcos.estik.files.domain.File;

@Mapper(componentModel = "spring")
public interface ChatFileMapper {    

    @Mapping(target = "fileUrl", source = "signedUrl")
    ChatFileInfo toInfo(File file, String signedUrl);

}
