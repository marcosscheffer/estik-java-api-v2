package com.marcos.estik.facility.adapter.out.user;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.marcos.estik.facility.application.ports.out.LoadFacilityUsersPort;
import com.marcos.estik.facility.domain.FacilityUser;
import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.application.pagination.PageResult;
import com.marcos.estik.user.application.port.in.FindAllUserByFacilityPort;

import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class LoadFacilityUsersAdapter implements LoadFacilityUsersPort{

    private final FindAllUserByFacilityPort findAllUserByFacilityPort;
    private final LoadFacilityUsersMapper loadFacilityUsersMapper;

    @Override
    public PageResult<FacilityUser> findAllFacilityUsers(UUID id, PageQuery page) {
        return findAllUserByFacilityPort.execute(id, page)
            .map(loadFacilityUsersMapper::toDomain);
    }
    
}
