package com.marcos.estik.chat.application.usecases;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marcos.estik.chat.application.ports.in.FindChatByIdPort;
import com.marcos.estik.chat.application.ports.out.AttachmentRepositoryPort;
import com.marcos.estik.chat.application.ports.out.ChatRepositoryPort;
import com.marcos.estik.chat.application.ports.out.LoadChatFindUserByIdPort;
import com.marcos.estik.chat.application.ports.out.MessageRepositoryPort;
import com.marcos.estik.chat.application.ports.out.ParticipantRepositoryPort;
import com.marcos.estik.chat.application.view.ChatAttachmentInfo;
import com.marcos.estik.chat.application.view.ChatMessageInfo;
import com.marcos.estik.chat.application.view.ChatParticipantInfo;
import com.marcos.estik.chat.application.view.ChatView;
import com.marcos.estik.chat.domain.Attachment;
import com.marcos.estik.chat.domain.Chat;
import com.marcos.estik.chat.domain.Message;
import com.marcos.estik.chat.domain.Participant;
import com.marcos.estik.chat.domain.exception.ChatForbiddenException;
import com.marcos.estik.security.AuthenticatedUser;
import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.application.pagination.PageResult;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FindChatByIdUseCase implements FindChatByIdPort {
    
    private final ChatRepositoryPort chatRepositoryPort;
    private final ParticipantRepositoryPort participantRepositoryPort;
    private final MessageRepositoryPort messageRepositoryPort;
    private final AttachmentRepositoryPort attachmentRepositoryPort;
    private final LoadChatFindUserByIdPort loadChatFindUserByIdPort;

    
    @Override
    public ChatView execute(UUID id, AuthenticatedUser principal, PageQuery page) {
        Boolean isParticipant = participantRepositoryPort.findByChatId(id).stream().anyMatch(
            participant -> participant.getUserId().equals(principal.id())
        );

        if (!isParticipant) {
            throw new ChatForbiddenException("You are not a participant of this chat");
        }

        Chat chat = chatRepositoryPort.findById(id);
        PageResult<Message> messagesPage = messageRepositoryPort.searchByChatId(id, page);
        List<Attachment> attachmentsList = attachmentRepositoryPort.findByMessageIds(
            messagesPage.items().stream().map(message -> message.getId()).toList()
        );
        Map<UUID, ChatParticipantInfo> participantsMap = toMapParticipantInfo(
            participantRepositoryPort.findByChatId(id)
        );

        return new ChatView(
            chat,
            participantsMap.values().stream().toList(),
            toMessageInfo(
                messagesPage, 
                toMapAttachmentInfo(attachmentsList), 
                participantsMap
            )
        );
    }

    private PageResult<ChatMessageInfo> toMessageInfo(
        PageResult<Message> messages, 
        Map<UUID, List<ChatAttachmentInfo>> attachments,
        Map<UUID, ChatParticipantInfo> participants
    ) {
        return messages.map(
            message -> new ChatMessageInfo(
                message.getId(),
                message.getChatId(),
                participants.get(message.getSenderId()),
                attachments.getOrDefault(message.getId(), null),
                message.getReplyToId(),
                message.getContent(),
                message.getCreatedAt(),
                message.getUpdatedAt()
            )
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

    private Map<UUID, List<ChatAttachmentInfo>> toMapAttachmentInfo(
        List<Attachment> attachments
    ) {
        return attachments.stream()
            .map(
                attachment -> new ChatAttachmentInfo(
                    attachment.getId(),
                    attachment.getMessageId(),
                    attachment.getFileName(),
                    attachment.getFileType(),
                    attachment.getFileSize(),
                    attachment.getFileUrl(),
                    attachment.getCreatedAt()
                )
            )
            .collect(Collectors.groupingBy(ChatAttachmentInfo::messageId));
    }
}
