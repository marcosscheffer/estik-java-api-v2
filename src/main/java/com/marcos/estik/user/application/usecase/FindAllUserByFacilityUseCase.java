package com.marcos.estik.user.application.usecase;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.application.pagination.PageResult;
import com.marcos.estik.user.application.port.in.FindAllUserByFacilityPort;
import com.marcos.estik.user.application.port.out.UserRepositoryPort;
import com.marcos.estik.user.domain.User;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class FindAllUserByFacilityUseCase implements FindAllUserByFacilityPort {

    private final UserRepositoryPort userRepositoryPort;

    @Override
    public PageResult<User> execute(UUID facilityId, PageQuery page) {
        return userRepositoryPort.FindAllByFacility(facilityId, page);
    }
}
