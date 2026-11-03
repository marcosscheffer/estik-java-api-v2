package com.marcos.estik.chat.adapter.in.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marcos.estik.chat.adapter.in.web.response.ChatResponse;
import com.marcos.estik.chat.application.ports.in.FindChatByIdPort;
import com.marcos.estik.chat.application.view.ChatMessageInfo;
import com.marcos.estik.chat.application.view.ChatView;
import com.marcos.estik.security.AuthenticatedUser;
import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.application.pagination.PageResult;

import lombok.RequiredArgsConstructor;

import java.util.UUID;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController 
@RequestMapping("/chats/{id}")
@RequiredArgsConstructor 
public class ChatController {

    private final FindChatByIdPort findChatByIdPort;

    @GetMapping
    public String getChat(
        @PathVariable UUID id,
        @AuthenticationPrincipal AuthenticatedUser principal,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "20") int size
    ) {
        ChatView chat = findChatByIdPort.execute(id, principal, new PageQuery(page, size));
        PageResult<ChatMessageInfo> messages = chat.messages()

        ChatResponse = 
        return 
    }
    
}
