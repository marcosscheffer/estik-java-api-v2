package com.marcos.estik.chat.adapter.out.file;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.marcos.estik.chat.application.ports.out.LoadChatFindFileByIdPort;
import com.marcos.estik.chat.application.view.ChatFileInfo;
import com.marcos.estik.files.application.ports.in.FindFileInfoByIdPort;
import com.marcos.estik.files.application.ports.in.SignFileUrlPort;
import com.marcos.estik.files.domain.File;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class LoadChatFindFileByIdAdapter implements LoadChatFindFileByIdPort {
    
    private final FindFileInfoByIdPort findFileInfoByIdPort;
    private final SignFileUrlPort signFileUrlPort;
    private final ChatFileMapper chatFileMapper;
    
    @Override
    public ChatFileInfo execute(UUID id) {
        File file = findFileInfoByIdPort.execute(id);
        return chatFileMapper.toInfo(
            file,
            signFileUrlPort.execute(file.getId())
        );
    }   
}
