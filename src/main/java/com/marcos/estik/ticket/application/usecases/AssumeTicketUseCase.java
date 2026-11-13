package com.marcos.estik.ticket.application.usecases;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marcos.estik.security.AuthenticatedUser;
import com.marcos.estik.shared.domain.event.TicketAssumedNotifyEvent;
import com.marcos.estik.ticket.application.ports.in.AssumeTicketPort;
import com.marcos.estik.ticket.application.ports.out.AssigneedRepositoryPort;
import com.marcos.estik.ticket.application.ports.out.ForwardedRepositoryPort;
import com.marcos.estik.ticket.application.ports.out.LoadTicketCreateChatPort;
import com.marcos.estik.ticket.application.ports.out.LoadTicketCreateParticipantPort;
import com.marcos.estik.ticket.application.ports.out.LoadTicketFindFacilityByIdPort;
import com.marcos.estik.ticket.application.ports.out.LoadTicketFindSpecializationByIdPort;
import com.marcos.estik.ticket.application.ports.out.LoadTicketFindUserByIdPort;
import com.marcos.estik.ticket.application.ports.out.TicketEventPublisherPort;
import com.marcos.estik.ticket.application.ports.out.TicketHistoryRepositoryPort;
import com.marcos.estik.ticket.application.ports.out.TicketRepositoryPort;
import com.marcos.estik.ticket.application.view.TicketAssigneedInfo;
import com.marcos.estik.ticket.application.view.TicketForwardedInfo;
import com.marcos.estik.ticket.application.view.TicketView;
import com.marcos.estik.ticket.domain.Assigneed;
import com.marcos.estik.ticket.domain.Ticket;
import com.marcos.estik.ticket.domain.TicketHistory;
import com.marcos.estik.ticket.domain.enums.StatusEnum;
import com.marcos.estik.ticket.domain.exception.TicketOwnerShipException;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class AssumeTicketUseCase implements AssumeTicketPort {

    private final TicketRepositoryPort ticketRepositoryPort;
    private final TicketHistoryRepositoryPort ticketHistoryRepositoryPort;
    private final AssigneedRepositoryPort assigneedRepositoryPort;
    private final ForwardedRepositoryPort forwardedRepositoryPort;
    private final LoadTicketFindFacilityByIdPort loadTicketFindFacilityByIdPort;
    private final LoadTicketFindUserByIdPort loadTicketFindUserByIdPort;
    private final LoadTicketCreateParticipantPort loadTicketCreateParticipantPort;
    private final LoadTicketCreateChatPort loadTicketCreateChatPort;
    private final TicketEventPublisherPort ticketEventPublisherPort;
    private final LoadTicketFindSpecializationByIdPort loadTicketFindSpecializationByIdPort;

    @Override
    @Transactional 
    public TicketView execute(UUID ticketId, AuthenticatedUser principal) {
        Ticket ticket = ticketRepositoryPort.findTicketById(ticketId);
        TicketHistory ticketHistory = TicketHistory.create(
            ticket.getId(), 
            StatusEnum.PROGRESS, 
            ticket.getSpecializationId()
        );

        List<TicketForwardedInfo> forwardedUsers = forwardedRepositoryPort.searchForwarded(ticket.getId())
            .stream()
            .map(
                forwardedUser -> new TicketForwardedInfo(
                    forwardedUser.getId(), 
                    loadTicketFindUserByIdPort.execute(forwardedUser.getUserId())
                )
            ).toList();
        
        if (!forwardedUsers.stream().anyMatch(forwardedUser -> forwardedUser.user().id().equals(principal.id()))) {
            throw new TicketOwnerShipException("ticket not forwarded for you");
        }

        Assigneed assigneed = Assigneed.create(principal.id(), ticketId);
        UUID chatId = ticket.getChatId() == null ? loadTicketCreateChatPort.execute() : ticket.getChatId();
        ticket.assign();
        loadTicketCreateParticipantPort.execute(assigneed.getUserId(), chatId);
        loadTicketCreateParticipantPort.execute(ticket.getRequesterId(), chatId);

        ticketRepositoryPort.save(ticket);
        ticketHistoryRepositoryPort.save(ticketHistory);
        assigneedRepositoryPort.save(assigneed);
        ticketEventPublisherPort.execute(
            new TicketAssumedNotifyEvent (
                ticket.getId(),
                ticket.getTitle(),
                ticket.getRequesterId(),
                principal.id()
            )
        );

        return new TicketView(
            ticket,
            loadTicketFindUserByIdPort.execute(ticket.getRequesterId()),
            loadTicketFindUserByIdPort.execute(ticket.getRegulatorId()),
            loadTicketFindFacilityByIdPort.execute(ticket.getFacilityId()),
            ticket.getSpecializationId() == null ? null : loadTicketFindSpecializationByIdPort.execute(ticket.getSpecializationId()),
            forwardedUsers,
            assigneedRepositoryPort.searchAssigneed(ticketId)
                .stream()
                .map(
                    assignee -> new TicketAssigneedInfo(
                        assignee.getId(),
                        loadTicketFindUserByIdPort.execute(assignee.getUserId())
                    )
                ).toList()
        );
    }
}
