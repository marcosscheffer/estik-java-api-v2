package com.marcos.estik.chat.application.ports.out;

import java.util.UUID;

import com.marcos.estik.chat.application.command.FileUpload;

public interface FileStoragePort {

    String store(UUID chatId, FileUpload file, String fileType);
    
}
