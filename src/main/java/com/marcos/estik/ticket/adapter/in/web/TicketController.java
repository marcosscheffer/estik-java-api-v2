package com.marcos.estik.ticket.adapter.in.web;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.marcos.estik.security.AuthenticatedUser;
import com.marcos.estik.ticket.adapter.in.web.mapper.TicketWebMapper;
import com.marcos.estik.ticket.adapter.in.web.request.CreateTicketRequest;
import com.marcos.estik.ticket.application.command.CreateTicketCommand;
import com.marcos.estik.ticket.application.ports.in.CreateTicketPort;
import com.marcos.estik.ticket.domain.Ticket;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping("/tickets")
@RequiredArgsConstructor 
public class TicketController {

    private final CreateTicketPort createTicketPort;
    private final TicketWebMapper ticketWebMapper;

    @PostMapping
    public ResponseEntity<?> createTicket(
        @RequestBody @Valid CreateTicketRequest request,
        @AuthenticationPrincipal AuthenticatedUser principal,
        UriComponentsBuilder uriBuilder
    ) {
        CreateTicketCommand command = ticketWebMapper.toCreateTicketCommand(request);
        Ticket ticket = createTicketPort.execute(command, principal.id());
        URI uri = uriBuilder
            .path("/tickets/{id}")
            .buildAndExpand(ticket.getId())
            .toUri();
        
        return ResponseEntity.created(uri).body(ticketWebMapper.toResponse(ticket));
    }
}
