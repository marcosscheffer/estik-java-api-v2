package com.marcos.estik.chat.adapter.out.file;

import org.springframework.stereotype.Component;

import com.marcos.estik.chat.application.ports.out.LoadChatStoreFilePort;
import com.marcos.estik.chat.application.view.ChatFileInfo;
import com.marcos.estik.files.application.ports.in.StoreFilePort;
import com.marcos.estik.shared.domain.file.FileUpload;

import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class LoadChatStoreFileUseCase implements LoadChatStoreFilePort {
    
    private final StoreFilePort storeFilePort;
    private final ChatFileMapper chatFileMapper;

    @Override
    public ChatFileInfo execute(String dir, FileUpload file) {
        return chatFileMapper.toInfo(
            storeFilePort.execute(dir, file)
        );
    }
}
