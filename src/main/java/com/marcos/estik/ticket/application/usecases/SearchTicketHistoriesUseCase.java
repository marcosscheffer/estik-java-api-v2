package com.marcos.estik.ticket.application.usecases;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.marcos.estik.security.AuthenticatedUser;
import com.marcos.estik.shared.application.pagination.PageResult;
import com.marcos.estik.shared.domain.user.PermissionEnum;
import com.marcos.estik.ticket.application.command.SearchTicketHistoriesCommand;
import com.marcos.estik.ticket.application.ports.in.SearchTicketHistoryPort;
import com.marcos.estik.ticket.application.ports.out.AssigneedRepositoryPort;
import com.marcos.estik.ticket.application.ports.out.ForwardedRepositoryPort;
import com.marcos.estik.ticket.application.ports.out.TicketHistoryRepositoryPort;
import com.marcos.estik.ticket.application.ports.out.TicketRepositoryPort;
import com.marcos.estik.ticket.domain.Ticket;
import com.marcos.estik.ticket.domain.TicketHistory;
import com.marcos.estik.ticket.domain.exception.TicketOwnerShipException;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class SearchTicketHistoriesUseCase implements SearchTicketHistoryPort {

    private final TicketHistoryRepositoryPort ticketHistoryRepositoryPort;
    private final TicketRepositoryPort ticketRepositoryPort;
    private final AssigneedRepositoryPort assigneedRepositoryPort;
    private final ForwardedRepositoryPort forwardedRepositoryPort;

    @Override
    public PageResult<TicketHistory> execute(UUID id, SearchTicketHistoriesCommand command, AuthenticatedUser principal) {
        Ticket ticket = ticketRepositoryPort.findTicketById(id);

        Boolean isAssignee = assigneedRepositoryPort.searchAssigneed(ticket.getId())
            .stream().anyMatch(a -> a.getUserId().equals(principal.id()));
        
        Boolean isForwarded = forwardedRepositoryPort.searchForwarded(ticket.getId())
            .stream().anyMatch(f -> f.getUserId().equals(principal.id()));

        if (!principal.id().equals(ticket.getRequesterId())
            && !isAssignee
            && !isForwarded
            && !principal.has(PermissionEnum.TICKET_MANAGE)
            && !(
                principal.has(PermissionEnum.TICKET_WORK)
                && principal.specializationId() != null
                && principal.specializationId().equals(ticket.getSpecializationId())
            )
        ) {
            throw new TicketOwnerShipException("You cannot view this ticket history");
        }

        return ticketHistoryRepositoryPort.searchTicketHistory(
            id, command.status(), command.page()
        );
    }
}
