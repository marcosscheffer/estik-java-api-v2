package com.marcos.estik.chat.adapter.out.user;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.marcos.estik.chat.application.ports.out.LoadChatFindUserByIdPort;
import com.marcos.estik.chat.application.view.ChatUserInfo;
import com.marcos.estik.user.application.port.in.FindUserByIdPort;
import com.marcos.estik.user.domain.User;

import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class LoadChatFindUserByIdAdapter implements LoadChatFindUserByIdPort {
    
    private final FindUserByIdPort findUserByIdPort;
    private final ChatUserMapper chatUserMapper;
    
    @Override
    public ChatUserInfo execute(UUID id) {
        User user = findUserByIdPort.execute(id);
        return chatUserMapper.toUserInfo(user);
    }
}
