package com.marcos.estik.user.adapter.in.web.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.marcos.estik.user.adapter.in.web.request.UpdateUserRequest;
import com.marcos.estik.user.adapter.in.web.response.UserResponse;
import com.marcos.estik.user.adapter.in.web.response.UserResponseSummary;
import com.marcos.estik.user.adapter.in.web.response.UserTicketResponse;
import com.marcos.estik.user.application.command.UpdateUserCommand;
import com.marcos.estik.user.domain.User;
import com.marcos.estik.user.domain.UserTicket;

@Mapper(componentModel = "spring")
public interface UserWebMapper {
    UserResponseSummary toResponseSummary(User user);

    UserResponse toResponse(User user);

    UserTicketResponse toUserTicketResponse(UserTicket ticket);

    @Mapping(target = "actorRole", ignore = true)
    UpdateUserCommand toUpdateUserCommand(UpdateUserRequest request);

}
