package com.marcos.estik.user.adapter.in.web;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.marcos.estik.security.AuthenticatedUser;
import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.application.pagination.PageResult;
import com.marcos.estik.user.adapter.in.web.mapper.UserWebMapper;
import com.marcos.estik.user.adapter.in.web.request.AssignUserRequest;
import com.marcos.estik.user.adapter.in.web.request.ChangeFacilityRequest;
import com.marcos.estik.user.adapter.in.web.request.ChangeSpecializationRequest;
import com.marcos.estik.user.adapter.in.web.request.UpdateUserRequest;
import com.marcos.estik.user.adapter.in.web.response.PageResponse;
import com.marcos.estik.user.adapter.in.web.response.UserResponse;
import com.marcos.estik.user.adapter.in.web.response.UserResponseSummary;
import com.marcos.estik.user.application.command.SearchUsersCommand;
import com.marcos.estik.user.application.port.in.ActiveUserByIdPort;
import com.marcos.estik.user.application.port.in.AssignUserByIdPort;
import com.marcos.estik.user.application.port.in.ChangeFacilityUserByIdPort;
import com.marcos.estik.user.application.port.in.ChangeSpecializationPort;
import com.marcos.estik.user.application.port.in.DeactiveUserByIdPort;
import com.marcos.estik.user.application.port.in.FindUserByIdPort;
import com.marcos.estik.user.application.port.in.SearchUsersPort;
import com.marcos.estik.user.application.port.in.UpdateUserPort;
import com.marcos.estik.user.application.view.UserView;

import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping("/users")
@RequiredArgsConstructor 
public class UserController {

    private final UpdateUserPort updateUserPort;
    private final SearchUsersPort searchUsersPort;
    private final FindUserByIdPort findUserByIdPort;
    private final UserWebMapper userWebMapper;
    private final DeactiveUserByIdPort deactiveUserByIdPort;
    private final ActiveUserByIdPort activeUserByIdPort;
    private final AssignUserByIdPort assignUserByIdPort;
    private final ChangeSpecializationPort changeSpecializationPort;
    private final ChangeFacilityUserByIdPort changeFacilityUserByIdPort;

    @GetMapping
    public ResponseEntity<PageResponse<UserResponseSummary>> getUsers(
        @RequestParam(defaultValue = "") String username,
        @RequestParam(required = false) UUID facilityId,
        @RequestParam(required = false) UUID roleId,
        @RequestParam(required = false) UUID specializationId,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "20") int size
    ) {
        PageResult<UserView> users = searchUsersPort.execute(
            new SearchUsersCommand (
                username,
                facilityId,
                roleId,
                specializationId,
                new PageQuery(page, size)
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
        UserView user = updateUserPort.execute(id, userWebMapper.toUpdateUserCommand(request), principal);
        return ResponseEntity.ok(userWebMapper.toResponse(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(
        @PathVariable UUID id
    ) {
        UserView user = findUserByIdPort.execute(id);
        return ResponseEntity.ok(userWebMapper.toResponse(user));
    }

    @PostMapping("/{id}/deactivate")
    public ResponseEntity<UserResponse> deactiveUser(
        @PathVariable UUID id,
        @AuthenticationPrincipal AuthenticatedUser principal
    ) {
        UserView user = deactiveUserByIdPort.execute(id, principal.id());

        return ResponseEntity.ok(userWebMapper.toResponse(user));
    }

    @PostMapping("/{id}/activate")
    public ResponseEntity<UserResponse> activeUser(
        @PathVariable UUID id
    ) {
        UserView user = activeUserByIdPort.execute(id);

        return ResponseEntity.ok(userWebMapper.toResponse(user));
    }

    @PostMapping("/{id}/assign")
    public ResponseEntity<UserResponse> assignUser(
        @PathVariable UUID id,
        @AuthenticationPrincipal AuthenticatedUser principal,
        @RequestBody AssignUserRequest request
    ) {
        UserView user = assignUserByIdPort.execute(id, principal.id(), request.role());

        return ResponseEntity.ok(userWebMapper.toResponse(user));
    }

    @PostMapping("/{id}/facility")
    public ResponseEntity<UserResponse> changeFacility(
        @PathVariable UUID id,
        @RequestBody ChangeFacilityRequest request
    ) {
        UserView user = changeFacilityUserByIdPort.execute(id, request.facilityId());

        return ResponseEntity.ok(userWebMapper.toResponse(user));
    }

    @PostMapping("/{id}/specialization")
    public ResponseEntity<UserResponse> changeSpecialization(
        @PathVariable UUID id,
        @RequestBody ChangeSpecializationRequest request
    ) {
        UserView user = changeSpecializationPort.execute(id, request.specializationId());

        return ResponseEntity.ok(userWebMapper.toResponse(user));
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponse> me(
        @AuthenticationPrincipal AuthenticatedUser principal
    ) {
        UserView user = findUserByIdPort.execute(principal.id());

        return ResponseEntity.ok(userWebMapper.toResponse(user));
    }
}
