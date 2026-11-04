package com.marcos.estik.chat.adapter.out.file;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.marcos.estik.chat.application.ports.out.LoadChatFindFileByIdPort;
import com.marcos.estik.chat.application.view.ChatFileInfo;
import com.marcos.estik.files.application.ports.in.FindFileInfoByIdPort;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class LoadChatFindFileByIdUseCase implements LoadChatFindFileByIdPort {
    
    private final FindFileInfoByIdPort findFileInfoByIdPort;
    private final ChatFileMapper chatFileMapper;
    
    @Override
    public ChatFileInfo execute(UUID id) {
        return chatFileMapper.toInfo(
            findFileInfoByIdPort.execute(id)
        );
    }   
}
