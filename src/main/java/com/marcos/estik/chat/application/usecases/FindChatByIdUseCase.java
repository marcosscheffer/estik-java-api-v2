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
        Boolean isParticipant = participantRepositoryPort.findByChatId(id).stream().anyMatch(
            participant -> participant.getUserId().equals(principal.id())
        );

        if (!isParticipant && !principal.permissions().contains(PermissionEnum.TICKET_MANAGE)) {
            throw new ChatForbiddenException("You are not a participant of this chat");
        }

        Chat chat = chatRepositoryPort.findById(id);
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

}
