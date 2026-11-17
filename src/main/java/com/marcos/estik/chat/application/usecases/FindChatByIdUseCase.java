package com.marcos.estik.chat.application.usecases;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marcos.estik.chat.application.ports.in.FindChatByIdPort;
import com.marcos.estik.chat.application.ports.out.ChatRepositoryPort;
import com.marcos.estik.chat.application.ports.out.LoadChatFindUserByIdPort;
import com.marcos.estik.chat.application.ports.out.ParticipantRepositoryPort;
import com.marcos.estik.chat.application.view.ChatParticipantInfo;
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

    
    @Override
    public ChatView execute(UUID id, AuthenticatedUser principal) {
        Chat chat = chatRepositoryPort.findById(id);
        Boolean canSupervise = chat.getType() == ChatTypeEnum.TICKET && 
            (principal.has(PermissionEnum.TICKET_SUPERVISOR) || principal.has(PermissionEnum.TICKET_MANAGE));
        if (!canSupervise) {
            Participant participant = participantRepositoryPort.findByUserIdAndChatId(principal.id(), chat.getId());
            if (participant == null || !participant.getActive()) {
                throw new ChatForbiddenException("You are not a participant of this chat");

            }
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
        ChatTicketInfo ticket = loadChatFindTicketByChatIdPort.execute(chat.getId());

    }

}
