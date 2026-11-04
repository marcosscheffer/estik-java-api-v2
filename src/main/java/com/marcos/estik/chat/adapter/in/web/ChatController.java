package com.marcos.estik.chat.adapter.in.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.marcos.estik.chat.adapter.in.sse.SseChatEmitter;
import com.marcos.estik.chat.adapter.in.web.mapper.ChatWebMapper;
import com.marcos.estik.chat.adapter.in.web.mapper.MessageWebMapper;
import com.marcos.estik.chat.adapter.in.web.request.SendMessageRequest;
import com.marcos.estik.chat.adapter.in.web.response.ChatResponse;
import com.marcos.estik.chat.adapter.in.web.response.MessageResponse;
import com.marcos.estik.chat.application.command.SendMessageCommand;
import com.marcos.estik.chat.application.ports.in.FindChatByIdPort;
import com.marcos.estik.chat.application.ports.in.SearchMessagesPort;
import com.marcos.estik.chat.application.ports.in.SendMessagePort;
import com.marcos.estik.chat.application.view.ChatView;
import com.marcos.estik.chat.application.view.MessageView;
import com.marcos.estik.security.AuthenticatedUser;
import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.application.pagination.PageResult;
import com.marcos.estik.shared.domain.file.FileUpload;

import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.List;
import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.PostMapping;

@RestController 
@RequestMapping("/chats/{id}")
@RequiredArgsConstructor 
public class ChatController {

    private final FindChatByIdPort findChatByIdPort;
    private final SearchMessagesPort searchMessagesPort;
    private final SendMessagePort sendMessagePort;
    private final ChatWebMapper chatWebMapper;
    private final MessageWebMapper messageWebMapper;

    @GetMapping
    public ResponseEntity<ChatResponse> getChat(
        @PathVariable UUID id,
        @AuthenticationPrincipal AuthenticatedUser principal
    ) {
        ChatView chat = findChatByIdPort.execute(id, principal);

        return ResponseEntity.ok(chatWebMapper.toResponse(chat));
    }

    @GetMapping("/messages")
    public ResponseEntity<PageResult<MessageResponse>> getMessages(
        @PathVariable UUID id,
        @AuthenticationPrincipal AuthenticatedUser principal,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "20") int size
    ) {
        PageResult<MessageView> messages = searchMessagesPort.execute(id, principal, new PageQuery(page, size));

        return ResponseEntity.ok(messages.map(messageWebMapper::toResponse));
    }

    @GetMapping(path = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter streamChat(
        @PathVariable UUID id,
        @AuthenticationPrincipal AuthenticatedUser principal
    ) {
        ChatView chat = findChatByIdPort.execute(id, principal);
        SseEmitter emitter = SseChatEmitter.createEmitter(chat.chat().getId());
        SseChatEmitter.connect(chat.chat().getId(), emitter);
        return emitter;
    }

    @PostMapping(path = "/messages", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<MessageResponse> sendMessage(
        @PathVariable UUID id,
        @AuthenticationPrincipal AuthenticatedUser principal,
        @RequestPart("message") SendMessageRequest request,
        @RequestPart(value = "files", required = false) List<MultipartFile> files
    ) {
            List<FileUpload> uploads = files == null 
                ? List.of() 
                : files.stream().map(this::toFileUpload).toList();
        
        SendMessageCommand command = new SendMessageCommand(
            request.replyToId(),
            request.content(),
            uploads
        );

        MessageView message = sendMessagePort.execute(id, command, principal);
        return ResponseEntity.ok(messageWebMapper.toResponse(message));
    }

    private FileUpload toFileUpload(MultipartFile file) {
        try {
            return new FileUpload(
                file.getName(),
                file.getSize(),
                file.getBytes()
            );
        } catch(IOException exception) {
            throw new UncheckedIOException("Failed to read uploaded file", exception);
        }
    }
}
    
