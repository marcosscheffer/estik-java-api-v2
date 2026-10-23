package com.marcos.estik.ticket.adapter.out.user;

import org.mapstruct.Mapper;

import com.marcos.estik.ticket.application.view.TicketUserInfo;
import com.marcos.estik.user.domain.User;

@Mapper(componentModel = "spring")
public interface TicketUserMapper {

    TicketUserInfo toUserInfo(User user);
    
}
