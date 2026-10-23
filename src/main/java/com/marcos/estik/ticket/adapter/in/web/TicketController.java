package com.marcos.estik.ticket.adapter.in.web;

import java.net.URI;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.marcos.estik.security.AuthenticatedUser;
import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.application.pagination.PageResult;
import com.marcos.estik.ticket.adapter.in.web.mapper.TicketHistoryWebMapper;
import com.marcos.estik.ticket.adapter.in.web.mapper.TicketWebMapper;
import com.marcos.estik.ticket.adapter.in.web.request.CreateTicketRequest;
import com.marcos.estik.ticket.adapter.in.web.request.UpdateTicketRequest;
import com.marcos.estik.ticket.adapter.in.web.response.TicketHistoryResponse;
import com.marcos.estik.ticket.adapter.in.web.response.TicketResponse;
import com.marcos.estik.ticket.adapter.in.web.response.TicketSummaryResponse;
import com.marcos.estik.ticket.application.command.CreateTicketCommand;
import com.marcos.estik.ticket.application.command.SearchTicketHistoriesCommand;
import com.marcos.estik.ticket.application.command.SearchTicketsCommand;
import com.marcos.estik.ticket.application.ports.in.AssumeTicketPort;
import com.marcos.estik.ticket.application.ports.in.CancelTicketPort;
import com.marcos.estik.ticket.application.ports.in.CreateTicketPort;
import com.marcos.estik.ticket.application.ports.in.DeleteTicketByIdPort;
import com.marcos.estik.ticket.application.ports.in.FindTicketByIdPort;
import com.marcos.estik.ticket.application.ports.in.ReopenTicketPort;
import com.marcos.estik.ticket.application.ports.in.ResolveTicketPort;
import com.marcos.estik.ticket.application.ports.in.SearchTicketHistoryPort;
import com.marcos.estik.ticket.application.ports.in.SearchTicketsPort;
import com.marcos.estik.ticket.application.ports.in.UpdateTicketPort;
import com.marcos.estik.ticket.application.view.TicketView;
import com.marcos.estik.ticket.domain.PriorityEnum;
import com.marcos.estik.ticket.domain.StatusEnum;
import com.marcos.estik.ticket.domain.TicketHistory;
import com.marcos.estik.ticket.domain.TicketTypeEnum;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController 
@RequestMapping("/tickets")
@RequiredArgsConstructor 
public class TicketController {

    private final CreateTicketPort createTicketPort;
    private final SearchTicketsPort searchTicketsPort;
    private final AssumeTicketPort assumeTicketPort;
    private final FindTicketByIdPort findTicketByIdPort;
    private final UpdateTicketPort updateTicketPort;
    private final TicketWebMapper ticketWebMapper;
    private final DeleteTicketByIdPort deleteTicketByIdPort;
    private final ResolveTicketPort resolveTicketPort;
    private final CancelTicketPort cancelTicketPort;
    private final ReopenTicketPort reopenTicketPort;
    private final SearchTicketHistoryPort searchTicketHistoryPort;
    private final TicketHistoryWebMapper ticketHistoryWebMapper;

    @GetMapping
    public ResponseEntity<PageResult<TicketSummaryResponse>> getTickets(
        @RequestParam(defaultValue = "") String title,
        @RequestParam(required = false) StatusEnum status,
        @RequestParam(required = false) PriorityEnum priority,
        @RequestParam(required = false) TicketTypeEnum type,
        @RequestParam(required = false) UUID facilityId,
        @RequestParam(required = false) UUID requesterId,
        @RequestParam(required = false) UUID assigneeId,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "20") int size
    ) {
        PageResult<TicketView> tickets = searchTicketsPort.execute(
            new SearchTicketsCommand(
                title,
                status,
                priority,
                type,
                facilityId,
                requesterId,
                assigneeId,
                new PageQuery(page, size)
            )
        );

        return ResponseEntity.ok(tickets.map(ticketWebMapper::toSummaryResponse));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketResponse> getTicket(
        @PathVariable UUID id
    ) {
        TicketView ticket = findTicketByIdPort.execute(id);
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
        TicketView ticket = createTicketPort.execute(command, principal);
        URI uri = uriBuilder
            .path("/tickets/{id}")
            .buildAndExpand(ticket.ticket().getId())
            .toUri();
        
        return ResponseEntity.created(uri).body(ticketWebMapper.toResponse(ticket));
    }

    @PostMapping("/{id}/assume") 
    public ResponseEntity<TicketResponse> assumeTicket(
        @PathVariable UUID id,
        @AuthenticationPrincipal AuthenticatedUser principal
    ) {
        TicketView ticket = assumeTicketPort.execute(id, principal);

        return ResponseEntity.ok(ticketWebMapper.toResponse(ticket));
    }

    @PostMapping("/{id}/resolve")
    public ResponseEntity<TicketResponse> resolveTicket(
        @PathVariable UUID id,
        @AuthenticationPrincipal AuthenticatedUser principal
    ) {
        TicketView ticket = resolveTicketPort.execute(id, principal);
        return ResponseEntity.ok(ticketWebMapper.toResponse(ticket));

    }

    @PostMapping("/{id}/reopen")
    public ResponseEntity<TicketResponse> reopenTicket(
        @PathVariable UUID id,
        @AuthenticationPrincipal AuthenticatedUser principal
    ){
        TicketView ticket = reopenTicketPort.execute(id, principal);
        return ResponseEntity.ok(ticketWebMapper.toResponse(ticket));
    }

    @PostMapping("/{id}/cancel")
    public ResponseEntity<TicketResponse> cancelTicket(
        @PathVariable UUID id,
        @AuthenticationPrincipal AuthenticatedUser principal
    ){
        TicketView ticket = cancelTicketPort.execute(id, principal);
        return ResponseEntity.ok(ticketWebMapper.toResponse(ticket));
    }


    @PutMapping("/{id}")
    public ResponseEntity<TicketResponse> updateTicket(
        @PathVariable UUID id,
        @RequestBody UpdateTicketRequest request,
        @AuthenticationPrincipal AuthenticatedUser principal
    ) {
        TicketView ticket = updateTicketPort.execute(
            ticketWebMapper.toUpdateTicketCommand(request), 
            id, 
            principal
        );

        return ResponseEntity.ok(ticketWebMapper.toResponse(ticket));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicket(
        @PathVariable UUID id
    ) {
        deleteTicketByIdPort.execute(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/histories")
    public ResponseEntity<PageResult<TicketHistoryResponse>> getTicketHistories(
        @PathVariable UUID id,
        @RequestParam(required = false) StatusEnum status,
        @RequestParam(required = false) TicketTypeEnum type,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "20") int size
    ) {
        PageResult<TicketHistory> ticketHistories = searchTicketHistoryPort.execute(
            id, 
            new SearchTicketHistoriesCommand(
                status,
                type,
                new PageQuery(page, size)
            )
        );

        return ResponseEntity.ok(ticketHistories.map(ticketHistoryWebMapper::toResponse));
    }
}
