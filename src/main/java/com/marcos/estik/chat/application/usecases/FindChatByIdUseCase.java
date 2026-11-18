package com.marcos.estik.chat.application.usecases;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marcos.estik.chat.application.ports.in.FindChatByIdPort;
import com.marcos.estik.chat.application.ports.out.ChatRepositoryPort;
import com.marcos.estik.chat.application.ports.out.LoadChatFindTicketByChatIdPort;
import com.marcos.estik.chat.application.ports.out.LoadChatFindUserByIdPort;
import com.marcos.estik.chat.application.ports.out.ParticipantRepositoryPort;
import com.marcos.estik.chat.application.view.ChatParticipantInfo;
import com.marcos.estik.chat.application.view.ChatTicketInfo;
import com.marcos.estik.chat.application.view.ChatView;
import com.marcos.estik.chat.domain.Chat;
import com.marcos.estik.chat.domain.Participant;
import com.marcos.estik.chat.domain.exception.ChatForbiddenException;
import com.marcos.estik.security.AuthenticatedUser;
import com.marcos.estik.shared.domain.chat.ChatTypeEnum;
import com.marcos.estik.shared.domain.user.PermissionEnum;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FindChatByIdUseCase implements FindChatByIdPort {
    
    private final ChatRepositoryPort chatRepositoryPort;
    private final ParticipantRepositoryPort participantRepositoryPort;
    private final LoadChatFindUserByIdPort loadChatFindUserByIdPort;
    private final LoadChatFindTicketByChatIdPort loadChatFindTicketByChatIdPort;

    
    @Override
    public ChatView execute(UUID id, AuthenticatedUser principal) {
        Chat chat = chatRepositoryPort.findById(id);
        if (!canRead(chat, principal)) {
            throw new ChatForbiddenException("you not be access this chat");
        }
        

        Map<UUID, ChatParticipantInfo> participantsMap = toMapParticipantInfo(
            participantRepositoryPort.findByChatId(id)
        );

        return new ChatView(
            chat,
            participantsMap.values().stream().toList()
        );
    }

    private Map<UUID, ChatParticipantInfo> toMapParticipantInfo(List<Participant> participants) {
        return participants
            .stream()
            .map(
                participant -> new ChatParticipantInfo(
                participant.getId(),
                participant.getChatId(),
                loadChatFindUserByIdPort.execute(participant.getUserId()),
                participant.getJoinedAt()
                )
            )
            .collect(Collectors.toMap(ChatParticipantInfo::id, Function.identity()));
    }

    private Boolean canRead(Chat chat, AuthenticatedUser principal) {
        Participant participant = participantRepositoryPort.findByUserIdAndChatId(principal.id(), chat.getId());
        
        if (participant != null && participant.getActive()) return true;

        if (chat.getType() != ChatTypeEnum.TICKET) return false;

        if (principal.has(PermissionEnum.TICKET_MANAGE)) {
            return  true;
        }

        if (principal.has(PermissionEnum.TICKET_SUPERVISOR)) {
            ChatTicketInfo ticket = loadChatFindTicketByChatIdPort.execute(chat.getId());
            return ticket != null && Objects.equals(principal.specializationId(), ticket.specializationId());
        }

        return false;
    }

}
