package com.marcos.estik.purchase.application.port.out;

import com.marcos.estik.purchase.application.view.PurchaseFileInfo;
import com.marcos.estik.shared.domain.file.FileUpload;

public interface LoadPurchaseStoreFilePort {
    
    PurchaseFileInfo execute(String dir, FileUpload file);

}
