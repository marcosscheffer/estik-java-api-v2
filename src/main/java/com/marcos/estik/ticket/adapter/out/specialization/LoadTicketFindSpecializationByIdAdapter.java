package com.marcos.estik.ticket.adapter.out.specialization;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.marcos.estik.specialization.application.ports.in.FindSpecializationByIdPort;
import com.marcos.estik.ticket.application.ports.out.LoadTicketFindSpecializationByIdPort;
import com.marcos.estik.ticket.application.view.TicketSpecializationInfo;

import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class LoadTicketFindSpecializationByIdAdapter implements LoadTicketFindSpecializationByIdPort {

    private final TicketSpecializationMapper ticketSpecializationMapper;
    private final FindSpecializationByIdPort findSpecializationByIdPort;

    @Override
    public TicketSpecializationInfo execute(UUID id) {
        return ticketSpecializationMapper.toInfo(findSpecializationByIdPort.execute(id));
    }
}
