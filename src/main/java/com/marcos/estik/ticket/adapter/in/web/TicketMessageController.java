package com.marcos.estik.ticket.adapter.in.web;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.marcos.estik.security.AuthenticatedUser;
import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.application.pagination.PageResult;
import com.marcos.estik.ticket.adapter.in.sse.SseTicketMessageEmitter;
import com.marcos.estik.ticket.adapter.in.web.mapper.TicketMessageWebMapper;
import com.marcos.estik.ticket.adapter.in.web.request.SendTicketMessageRequest;
import com.marcos.estik.ticket.adapter.in.web.response.TicketMessageResponse;
import com.marcos.estik.ticket.application.ports.in.CheckTicketMessagePort;
import com.marcos.estik.ticket.application.ports.in.SearchTicketMessagePort;
import com.marcos.estik.ticket.application.ports.in.SendTicketMessagePort;
import com.marcos.estik.ticket.application.view.TicketMessageView;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping("/tickets/{id}/messages")
@RequiredArgsConstructor 
public class TicketMessageController {
    private final CheckTicketMessagePort checkTicketMessagePort;
    private final SendTicketMessagePort sendTicketMessagePort;
    private final SearchTicketMessagePort searchTicketMessagePort;
    private final TicketMessageWebMapper ticketMessageWebMapper;

    @GetMapping
    public ResponseEntity<PageResult<TicketMessageResponse>> getMessages(
        @PathVariable UUID id,
        @AuthenticationPrincipal AuthenticatedUser principal,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "50") int size
    ) {
        PageResult<TicketMessageView> messages = searchTicketMessagePort.execute(id, new PageQuery(page, size));

        return ResponseEntity.ok(messages.map(ticketMessageWebMapper::toResponse));
    }

    @PostMapping
    public ResponseEntity<TicketMessageResponse> sendMessage(
        @PathVariable UUID id,
        @RequestBody @Valid  SendTicketMessageRequest request,
        @AuthenticationPrincipal AuthenticatedUser principal
    ) {
        TicketMessageView message = sendTicketMessagePort.execute(id, request.body(), principal);
        return ResponseEntity.status(HttpStatus.CREATED).body(ticketMessageWebMapper.toResponse(message));
    }

    @GetMapping(path = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter sseMessages(
        @PathVariable UUID id,
        @AuthenticationPrincipal AuthenticatedUser principal
    ) {
        checkTicketMessagePort.execute(id, principal);
        SseEmitter emitter = SseTicketMessageEmitter.createEmitter(id);
        return emitter;
    }
    
}
