package com.marcos.estik.user.adapter.out.specialization;

import org.mapstruct.Mapper;

import com.marcos.estik.specialization.domain.Specialization;
import com.marcos.estik.user.application.view.UserSpecializationInfo;

@Mapper(componentModel = "spring")
public interface UserSpecializationMapper {

    UserSpecializationInfo toInfo(Specialization specialization);
    
}
