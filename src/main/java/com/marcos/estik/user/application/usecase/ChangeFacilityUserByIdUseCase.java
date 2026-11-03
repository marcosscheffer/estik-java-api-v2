package com.marcos.estik.user.application.usecase;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.marcos.estik.user.application.port.in.ChangeFacilityUserByIdPort;
import com.marcos.estik.user.application.port.out.LoadUserExistsFacilityByIdPort;
import com.marcos.estik.user.application.port.out.UserRepositoryPort;
import com.marcos.estik.user.domain.User;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class ChangeFacilityUserByIdUseCase implements ChangeFacilityUserByIdPort {

    private final UserRepositoryPort userRepositoryPort;
    private final LoadUserExistsFacilityByIdPort loadUserExistsFacilityByIdPort;

    @Override
    public User execute(UUID userId, UUID facilityId) {
        if (!loadUserExistsFacilityByIdPort.existsById(facilityId)) {
            throw new EntityNotFoundException("Facility not found");
        }
        System.out.println(123);
        User user = userRepositoryPort.findById(userId);
        user.moveTo(facilityId);
        return userRepositoryPort.save(user);
    }
    
}
