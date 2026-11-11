package com.marcos.estik.user.adapter.out.specialization;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.marcos.estik.specialization.application.ports.in.FindSpecializationByIdPort;
import com.marcos.estik.user.application.port.out.LoadUserFindSpecializationByIdPort;
import com.marcos.estik.user.application.view.UserSpecializationInfo;

import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class LoadUserFindSpecializationByIdAdapter implements LoadUserFindSpecializationByIdPort {

    private final FindSpecializationByIdPort findSpecializationByIdPort;
    private final UserSpecializationMapper userSpecializationMapper;
    
    @Override
    public UserSpecializationInfo execute(UUID id) {
        return userSpecializationMapper.toInfo(findSpecializationByIdPort.execute(id));
    }
}