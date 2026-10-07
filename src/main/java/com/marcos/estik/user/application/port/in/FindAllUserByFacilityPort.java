package com.marcos.estik.user.application.port.in;

import java.util.UUID;

import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.application.pagination.PageResult;
import com.marcos.estik.user.domain.User;

public interface FindAllUserByFacilityPort {
    PageResult<User> execute(UUID facilityId, PageQuery page);
}
