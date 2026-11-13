package com.marcos.estik.ticket.application.usecases;

import org.springframework.stereotype.Service;

import com.marcos.estik.ticket.application.command.CreateAssigneedCommand;
import com.marcos.estik.ticket.application.ports.in.CreateAssigneedPort;
import com.marcos.estik.ticket.application.ports.out.AssigneedRepositoryPort;
import com.marcos.estik.ticket.domain.Assigneed;
import com.marcos.estik.ticket.domain.exception.UserAlreadyAssigneedException;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class CreateAssigneedUseCase implements CreateAssigneedPort {
    
    private final AssigneedRepositoryPort assigneedRepositoryPort;

    @Override
    public Assigneed execute(CreateAssigneedCommand command) {

        if (assigneedRepositoryPort.existsByTicketIdAndUserId(command.ticketId(), command.userId())) {
            throw new UserAlreadyAssigneedException("already assigneed to this user");
        }

        Assigneed assigneed = Assigneed.create(
            command.userId(), 
            command.ticketId()
        );

        assigneedRepositoryPort.save(assigneed);
        return assigneed;
    }
}
