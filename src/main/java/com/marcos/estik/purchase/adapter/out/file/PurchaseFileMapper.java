package com.marcos.estik.purchase.adapter.out.file;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.marcos.estik.files.domain.File;
import com.marcos.estik.purchase.application.view.PurchaseFileInfo;

@Mapper(componentModel = "spring")
public interface PurchaseFileMapper {

    @Mapping(target = "fileUrl", source = "signedUrl")
    PurchaseFileInfo toInfo(File file, String signedUrl);
    
}
