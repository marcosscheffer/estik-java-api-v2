package com.marcos.estik.chat.application.ports.out;


import com.marcos.estik.chat.application.view.ChatFileInfo;
import com.marcos.estik.shared.domain.file.FileUpload;

public interface LoadChatStoreFilePort {
    
    ChatFileInfo execute(String dir, FileUpload file);

}
