package com.marcos.estik.facility.adapter.out.user;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.marcos.estik.facility.domain.FacilityUser;
import com.marcos.estik.user.domain.User;

@Mapper(componentModel = "spring")
public interface LoadFacilityUsersMapper {

    @Mapping(target = "userId", source = "id")
    FacilityUser toDomain(User user);
}
