package com.marcos.estik.ticket.application.usecases;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.marcos.estik.ticket.application.ports.in.SearchAssigneedPort;
import com.marcos.estik.ticket.application.ports.out.AssigneedRepositoryPort;
import com.marcos.estik.ticket.domain.Assigneed;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class SearchAssigneedUseCase implements SearchAssigneedPort {

    private final AssigneedRepositoryPort assigneedRepositoryPort;

    @Override
    public List<Assigneed> execute(UUID ticketId) {
        return assigneedRepositoryPort.searchAssigneed(ticketId);
    }
}
