package com.marcos.estik.ticket.application.usecases;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marcos.estik.ticket.application.ports.in.FindTicketByChatIdPort;
import com.marcos.estik.ticket.application.ports.out.AssigneedRepositoryPort;
import com.marcos.estik.ticket.application.ports.out.ForwardedRepositoryPort;
import com.marcos.estik.ticket.application.ports.out.LoadTicketFindFacilityByIdPort;
import com.marcos.estik.ticket.application.ports.out.LoadTicketFindSpecializationByIdPort;
import com.marcos.estik.ticket.application.ports.out.LoadTicketFindUserByIdPort;
import com.marcos.estik.ticket.application.ports.out.TicketRepositoryPort;
import com.marcos.estik.ticket.application.view.TicketAssigneedInfo;
import com.marcos.estik.ticket.application.view.TicketForwardedInfo;
import com.marcos.estik.ticket.application.view.TicketView;
import com.marcos.estik.ticket.domain.Ticket;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class FindTicketByChatIdUseCase implements FindTicketByChatIdPort {

    private final TicketRepositoryPort ticketRepositoryPort;
    private final LoadTicketFindUserByIdPort loadTicketFindUserByIdPort;
    private final LoadTicketFindFacilityByIdPort loadTicketFindFacilityByIdPort;
    private final LoadTicketFindSpecializationByIdPort loadTicketFindSpecializationByIdPort;
    private final ForwardedRepositoryPort forwardedRepositoryPort;
    private final AssigneedRepositoryPort assigneedRepositoryPort;

    
    @Override
    @Transactional 
    public TicketView execute(UUID chatId) {
        Ticket ticket = ticketRepositoryPort.findByChatId(chatId);
        return new TicketView(
            ticket,
            loadTicketFindUserByIdPort.execute(ticket.getRequesterId()),
            ticket.getRegulatorId() == null ? null : loadTicketFindUserByIdPort.execute(ticket.getRegulatorId()),
            loadTicketFindFacilityByIdPort.execute(ticket.getFacilityId()),
            ticket.getSpecializationId() == null ? null : loadTicketFindSpecializationByIdPort.execute(ticket.getSpecializationId()),
            forwardedRepositoryPort.searchForwarded(ticket.getId())
                .stream()
                .map(forwarededUser -> new TicketForwardedInfo(forwarededUser.getId(), loadTicketFindUserByIdPort.execute(forwarededUser.getUserId())))
                .toList(),
            assigneedRepositoryPort.searchAssigneed(ticket.getId())
                .stream()
                .map(assigneedUser -> new TicketAssigneedInfo(assigneedUser.getId(), loadTicketFindUserByIdPort.execute(assigneedUser.getUserId())))
                .toList()
        );
    }
}
