package com.marcos.estik.user.application.usecase;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.marcos.estik.user.application.port.in.ChangeFacilityUserByIdPort;
import com.marcos.estik.user.application.port.out.LoadUserVerifyFacilityExistsByIdPort;
import com.marcos.estik.user.application.port.out.UserRepositoryPort;
import com.marcos.estik.user.domain.User;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class ChangeFacilityUserByIdUseCase implements ChangeFacilityUserByIdPort {

    private final UserRepositoryPort userRepositoryPort;
    private final LoadUserVerifyFacilityExistsByIdPort loadUserVerifyFacilityExistsByIdPort;

    @Override
    public User execute(UUID userId, UUID facilityId) {
        User user = userRepositoryPort.findById(userId);
        loadUserVerifyFacilityExistsByIdPort.existsById(facilityId);
        user.moveTo(facilityId);
        return userRepositoryPort.save(user);
    }
    
}
