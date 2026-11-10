package com.marcos.estik.user.adapter.in.web.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.marcos.estik.user.adapter.in.web.request.UpdateUserRequest;
import com.marcos.estik.user.adapter.in.web.response.UserResponse;
import com.marcos.estik.user.adapter.in.web.response.UserResponseSummary;
import com.marcos.estik.user.application.command.UpdateUserCommand;
import com.marcos.estik.user.application.view.UserView;

@Mapper(componentModel = "spring")
public interface UserWebMapper {

    @Mapping(target = ".", source = "user")
    UserResponseSummary toResponseSummary(UserView user);

    @Mapping(target = ".", source = "user")
    UserResponse toResponse(UserView user);

    UpdateUserCommand toUpdateUserCommand(UpdateUserRequest request);

}
