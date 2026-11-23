package com.marcos.estik.purchase.adapter.out.file;

import org.mapstruct.Mapper;

import com.marcos.estik.files.domain.File;
import com.marcos.estik.purchase.application.view.PurchaseFileInfo;

@Mapper(componentModel = "spring")
public interface PurchaseFileMapper {

    PurchaseFileInfo toInfo(File file);
    
}
