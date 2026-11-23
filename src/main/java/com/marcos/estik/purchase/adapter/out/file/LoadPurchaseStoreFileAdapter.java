package com.marcos.estik.purchase.adapter.out.file;

import org.springframework.stereotype.Component;

import com.marcos.estik.files.application.ports.in.StoreFilePort;
import com.marcos.estik.files.domain.File;
import com.marcos.estik.purchase.application.port.out.LoadPurchaseStoreFilePort;
import com.marcos.estik.purchase.application.view.PurchaseFileInfo;
import com.marcos.estik.shared.domain.file.FileUpload;

import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class LoadPurchaseStoreFileAdapter implements LoadPurchaseStoreFilePort {

    private final StoreFilePort storeFilePort;
    private final PurchaseFileMapper purchaseFileMapper;

    @Override
    public PurchaseFileInfo execute(String dir, FileUpload file) {
        File fileSaved = storeFilePort.execute(dir, file);
        return purchaseFileMapper.toInfo(fileSaved);
    }
}
