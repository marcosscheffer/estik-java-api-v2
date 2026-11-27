package com.marcos.estik.solution.adapter.out.user;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.marcos.estik.solution.application.view.SolutionUserInfo;
import com.marcos.estik.user.application.view.UserView;

@Mapper(componentModel = "spring")
public interface SolutionUserMapper {

    @Mapping(target = ".", source = "user")
    SolutionUserInfo toInfo(UserView user);
    
}
