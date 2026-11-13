package com.marcos.estik.ticket.application.usecases;

import org.springframework.stereotype.Service;

import com.marcos.estik.ticket.application.command.CreateForwardedCommand;
import com.marcos.estik.ticket.application.ports.in.CreateForwardedPort;
import com.marcos.estik.ticket.application.ports.out.ForwardedRepositoryPort;
import com.marcos.estik.ticket.domain.Forwarded;
import com.marcos.estik.ticket.domain.exception.UserAlreadyForwardedException;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class CreateForwardedUseCase implements CreateForwardedPort {
    
    private final ForwardedRepositoryPort forwardedRepositoryPort;

    @Override
    public Forwarded execute(CreateForwardedCommand command) {
        if (forwardedRepositoryPort.existsByTicketIdAndUserId(command.ticketId(), command.userId())) {
            throw new UserAlreadyForwardedException("already forwarded to this user");
        }

        Forwarded forwarded = Forwarded.create(command.userId(), command.ticketId());
        forwardedRepositoryPort.save(forwarded);
        return forwarded;
    }
}
