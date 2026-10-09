package com.marcos.estik.ticket.adapter.out.user;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.marcos.estik.ticket.application.ports.out.VerifyUserExistsPort;
import com.marcos.estik.user.application.port.in.VerifyUserExistsByIdPort;

import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class VerifyUserByIdAdapter implements VerifyUserExistsPort {
    private final VerifyUserExistsByIdPort verifyUserExistsByIdPort;

    @Override
    public void existsById(UUID id) {
        verifyUserExistsByIdPort.execute(id);
    }
}
