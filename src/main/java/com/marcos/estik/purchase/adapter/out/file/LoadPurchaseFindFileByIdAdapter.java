package com.marcos.estik.purchase.adapter.out.file;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.marcos.estik.files.application.ports.in.FindFileInfoByIdPort;
import com.marcos.estik.purchase.application.port.out.LoadPurchaseFindFileByIdPort;
import com.marcos.estik.purchase.application.view.PurchaseFileInfo;

import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class LoadPurchaseFindFileByIdAdapter implements LoadPurchaseFindFileByIdPort {

    private final FindFileInfoByIdPort fileInfoByIdPort;
    private final PurchaseFileMapper purchaseFileMapper;
    
    @Override
    public PurchaseFileInfo execute(UUID id) {
        return purchaseFileMapper.toInfo(fileInfoByIdPort.execute(id));
    }
}
