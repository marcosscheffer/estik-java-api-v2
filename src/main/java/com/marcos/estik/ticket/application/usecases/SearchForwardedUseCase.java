package com.marcos.estik.ticket.application.usecases;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.marcos.estik.ticket.application.ports.in.SearchForwardedPort;
import com.marcos.estik.ticket.application.ports.out.ForwardedRepositoryPort;
import com.marcos.estik.ticket.domain.Forwarded;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class SearchForwardedUseCase implements SearchForwardedPort {
    
    private final ForwardedRepositoryPort forwardedRepositoryPort;

    @Override
    public List<Forwarded> execute(UUID ticketId) {
        return forwardedRepositoryPort.searchForwarded(ticketId);
    }
}
