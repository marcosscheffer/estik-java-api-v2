package com.marcos.estik.user.application.usecase;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.marcos.estik.user.application.port.in.VerifyUserExistsByIdPort;
import com.marcos.estik.user.application.port.out.UserRepositoryPort;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class VerifyUserExistsById implements VerifyUserExistsByIdPort {

    private final UserRepositoryPort userRepositoryPort;

    @Override
    public void execute(UUID id) {
        if (!userRepositoryPort.existsById(id)) {
            throw new EntityNotFoundException("User not found");
        }
    }
    
}
