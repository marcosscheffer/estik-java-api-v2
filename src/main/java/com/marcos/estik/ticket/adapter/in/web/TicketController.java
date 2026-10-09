package com.marcos.estik.ticket.adapter.in.web;

import java.net.URI;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.marcos.estik.security.AuthenticatedUser;
import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.application.pagination.PageResult;
import com.marcos.estik.ticket.adapter.in.web.mapper.TicketWebMapper;
import com.marcos.estik.ticket.adapter.in.web.request.CreateTicketRequest;
import com.marcos.estik.ticket.adapter.in.web.response.TicketResponse;
import com.marcos.estik.ticket.adapter.in.web.response.TicketSummaryResponse;
import com.marcos.estik.ticket.application.command.AssumeTicketCommand;
import com.marcos.estik.ticket.application.command.CreateTicketCommand;
import com.marcos.estik.ticket.application.command.SearchTicketsByTitleAndPriorityAndStatusCommand;
import com.marcos.estik.ticket.application.ports.in.AssumeTicketPort;
import com.marcos.estik.ticket.application.ports.in.CreateTicketPort;
import com.marcos.estik.ticket.application.ports.in.FindTicketByIdPort;
import com.marcos.estik.ticket.application.ports.in.SearchTicketsByTitleAndPriorityAndStatusPort;
import com.marcos.estik.ticket.domain.PriorityEnum;
import com.marcos.estik.ticket.domain.StatusEnum;
import com.marcos.estik.ticket.domain.Ticket;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController 
@RequestMapping("/tickets")
@RequiredArgsConstructor 
public class TicketController {

    private final CreateTicketPort createTicketPort;
    private final SearchTicketsByTitleAndPriorityAndStatusPort searchTicketsByTitleAndPriorityAndStatusPort;
    private final AssumeTicketPort assumeTicketPort;
    private final FindTicketByIdPort findTicketByIdPort;
    private final TicketWebMapper ticketWebMapper;

    @GetMapping
    public ResponseEntity<PageResult<TicketSummaryResponse>> getTickets(
        @RequestParam(defaultValue = "") String q,
        @RequestParam(required = false) StatusEnum status,
        @RequestParam(required = false) PriorityEnum priority,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "20") int size
    ) {
        PageResult<Ticket> tickets = searchTicketsByTitleAndPriorityAndStatusPort.execute(
            new SearchTicketsByTitleAndPriorityAndStatusCommand(
                q,
                status,
                priority,
                new PageQuery(page, size)
            )
        );

        return ResponseEntity.ok(tickets.map(ticketWebMapper::toSummaryResponse));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketResponse> getTicket(
        @PathVariable UUID id
    ) {
        Ticket ticket = findTicketByIdPort.execute(id);
        return ResponseEntity.ok(
            ticketWebMapper.toResponse(ticket)
        );
    }
    
    @PostMapping
    public ResponseEntity<TicketResponse> createTicket(
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

    @PostMapping("/{id}/assume") 
    public ResponseEntity<TicketResponse> assumeTicket(
        @PathVariable UUID id,
        @AuthenticationPrincipal AuthenticatedUser principal
    ) {
        Ticket ticket = assumeTicketPort.execute(new AssumeTicketCommand(
            id, principal.id()
        ));

        return ResponseEntity.ok(ticketWebMapper.toResponse(ticket));
    }
}
