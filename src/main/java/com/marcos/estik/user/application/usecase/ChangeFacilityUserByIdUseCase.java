package com.marcos.estik.user.application.usecase;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.marcos.estik.user.application.port.in.ChangeFacilityUserByIdPort;
import com.marcos.estik.user.application.port.out.LoadUserExistsFacilityByIdPort;
import com.marcos.estik.user.application.port.out.LoadUserFindRoleByIdPort;
import com.marcos.estik.user.application.port.out.LoadUserFindSpecializationByIdPort;
import com.marcos.estik.user.application.port.out.UserRepositoryPort;
import com.marcos.estik.user.application.view.UserView;
import com.marcos.estik.user.domain.User;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class ChangeFacilityUserByIdUseCase implements ChangeFacilityUserByIdPort {

    private final UserRepositoryPort userRepositoryPort;
    private final LoadUserExistsFacilityByIdPort loadUserExistsFacilityByIdPort;
    private final LoadUserFindRoleByIdPort loadUserFindRoleByIdPort;
    private final LoadUserFindSpecializationByIdPort loadUserFindSpecializationByIdPort;


    @Override
    public UserView execute(UUID userId, UUID facilityId) {
        if (!loadUserExistsFacilityByIdPort.existsById(facilityId)) {
            throw new EntityNotFoundException("Facility not found");
        }
        System.out.println(123);
        User user = userRepositoryPort.findById(userId);
        user.moveTo(facilityId);
        userRepositoryPort.save(user);

        return new UserView(
            user, 
            loadUserFindRoleByIdPort.execute(user.getRoleId()),
            loadUserFindSpecializationByIdPort.execute(user.getSpecializationId())
        );
    }
    
}
