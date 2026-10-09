package com.marcos.estik.user.adapter.in.web;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.marcos.estik.security.AuthenticatedUser;
import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.application.pagination.PageResult;
import com.marcos.estik.user.adapter.in.web.mapper.UserWebMapper;
import com.marcos.estik.user.adapter.in.web.request.UpdateUserRequest;
import com.marcos.estik.user.adapter.in.web.response.PageResponse;
import com.marcos.estik.user.adapter.in.web.response.UserResponse;
import com.marcos.estik.user.adapter.in.web.response.UserResponseSummary;
import com.marcos.estik.user.adapter.in.web.response.UserTicketResponse;
import com.marcos.estik.user.application.command.SearchByIdCommand;
import com.marcos.estik.user.application.command.UpdateUserCommand;
import com.marcos.estik.user.application.port.in.FindTicketsByUserPort;
import com.marcos.estik.user.application.port.in.FindUserByIdPort;
import com.marcos.estik.user.application.port.in.SearchUserByIdPort;
import com.marcos.estik.user.application.port.in.UpdateUserPort;
import com.marcos.estik.user.domain.User;
import com.marcos.estik.user.domain.UserTicket;

import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping("/users")
@RequiredArgsConstructor 
public class UserController {

    private final SearchUserByIdPort searchByIdPort;
    private final UpdateUserPort updateUserPort;
    private final FindUserByIdPort findUserByIdPort;
    private final FindTicketsByUserPort findTicketsByUserPort;
    private final UserWebMapper userWebMapper;

    @GetMapping
    public ResponseEntity<PageResponse<UserResponseSummary>> getUsers(
        @RequestParam(defaultValue = "") String q,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "20") int size
    ) {
        PageResult<User> users = searchByIdPort.execute(
            new SearchByIdCommand(
                q, page, size
            )
        );

       PageResponse<UserResponseSummary> pageResponse = new PageResponse<UserResponseSummary>(
            users.items().stream().map(userWebMapper::toResponseSummary).toList(),
            users.page(),
            users.size(),
            users.totalItems(),
            users.totalPages()
       );

       return ResponseEntity.ok(pageResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(
        @PathVariable UUID id,
        @RequestBody UpdateUserRequest request,
        @AuthenticationPrincipal AuthenticatedUser principal
    ) {
        UpdateUserCommand command = userWebMapper.toUpdateUserCommand(request);
        command.setActorRole(principal.role());
        command.setId(id);
        User user = updateUserPort.execute(command);

        return ResponseEntity.ok(userWebMapper.toResponse(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(
        @PathVariable UUID id
    ) {
        User user = findUserByIdPort.execute(id);
        return ResponseEntity.ok(userWebMapper.toResponse(user));
    }

    @GetMapping("/{id}/tickets")
    public ResponseEntity<PageResult<UserTicketResponse>> getUserTickets(
        @PathVariable UUID id,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "20") int size,
        @AuthenticationPrincipal AuthenticatedUser principal
    ) {
        PageResult<UserTicket> tickets = findTicketsByUserPort.execute(
            id,
            new PageQuery(page, size)
        );

        return ResponseEntity.ok(tickets.map(userWebMapper::toUserTicketResponse));
    }
}
