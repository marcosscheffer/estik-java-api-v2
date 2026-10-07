package com.marcos.estik.user.adapter.in.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.marcos.estik.shared.application.pagination.PageResult;
import com.marcos.estik.user.adapter.in.web.mapper.UserWebMapper;
import com.marcos.estik.user.adapter.in.web.response.PageResponse;
import com.marcos.estik.user.adapter.in.web.response.UserResponseSummary;
import com.marcos.estik.user.application.command.SearchByIdCommand;
import com.marcos.estik.user.application.port.in.SearchUserByIdPort;
import com.marcos.estik.user.domain.User;

import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping("/users")
@RequiredArgsConstructor 
public class UserController {

    private final SearchUserByIdPort searchByIdPort;
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
}
