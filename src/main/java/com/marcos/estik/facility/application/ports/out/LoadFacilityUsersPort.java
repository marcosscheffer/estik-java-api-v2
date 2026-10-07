package com.marcos.estik.facility.application.ports.out;

import java.util.UUID;

import com.marcos.estik.facility.domain.FacilityUser;
import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.application.pagination.PageResult;

public interface LoadFacilityUsersPort {
    PageResult<FacilityUser> findAllFacilityUsers(UUID id, PageQuery page);
}
