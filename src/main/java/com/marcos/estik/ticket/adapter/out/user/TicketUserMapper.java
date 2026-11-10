package com.marcos.estik.ticket.adapter.out.user;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.marcos.estik.ticket.application.view.TicketUserInfo;
import com.marcos.estik.user.application.view.UserView;

@Mapper(componentModel = "spring")
public interface TicketUserMapper {

    @Mapping(target = ".", source = "user")
    TicketUserInfo toUserInfo(UserView user);
    
}
