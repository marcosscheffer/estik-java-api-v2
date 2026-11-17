package com.marcos.estik.chat.application.usecases;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.marcos.estik.chat.application.ports.in.SearchMessagesPort;
import com.marcos.estik.chat.application.ports.out.AttachmentRepositoryPort;
import com.marcos.estik.chat.application.ports.out.ChatRepositoryPort;
import com.marcos.estik.chat.application.ports.out.LoadChatFindFileByIdPort;
import com.marcos.estik.chat.application.ports.out.LoadChatFindTicketByChatIdPort;
import com.marcos.estik.chat.application.ports.out.LoadChatFindUserByIdPort;
import com.marcos.estik.chat.application.ports.out.MessageRepositoryPort;
import com.marcos.estik.chat.application.ports.out.ParticipantRepositoryPort;
import com.marcos.estik.chat.application.view.ChatAttachmentInfo;
import com.marcos.estik.chat.application.view.ChatParticipantInfo;
import com.marcos.estik.chat.application.view.ChatTicketInfo;
import com.marcos.estik.chat.application.view.MessageInfo;
import com.marcos.estik.chat.application.view.MessageView;
import com.marcos.estik.chat.domain.Attachment;
import com.marcos.estik.chat.domain.Chat;
import com.marcos.estik.chat.domain.Message;
import com.marcos.estik.chat.domain.Participant;
import com.marcos.estik.chat.domain.exception.ChatForbiddenException;
import com.marcos.estik.security.AuthenticatedUser;
import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.application.pagination.PageResult;
import com.marcos.estik.shared.domain.chat.ChatTypeEnum;
import com.marcos.estik.shared.domain.user.PermissionEnum;

import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class SearchMessagesUseCase implements SearchMessagesPort {
    
    private final ChatRepositoryPort chatRepositoryPort;
    private final ParticipantRepositoryPort participantRepositoryPort;
    private final AttachmentRepositoryPort attachmentRepositoryPort;
    private final MessageRepositoryPort messageRepositoryPort;
    private final LoadChatFindFileByIdPort loadChatFindFileByIdPort;
    private final LoadChatFindUserByIdPort loadChatFindUserByIdPort;
    private final LoadChatFindTicketByChatIdPort loadChatFindTicketByChatIdPort;

    @Override
    @Transactional(readOnly = true)
    public PageResult<MessageView> execute(UUID chatId, AuthenticatedUser principal, PageQuery page) {
        Chat chat = chatRepositoryPort.findById(chatId);
        Boolean canSupervise = chat.getType() == ChatTypeEnum.TICKET 
            && (principal.has(PermissionEnum.TICKET_SUPERVISOR) || principal.has(PermissionEnum.TICKET_MANAGE));
        if (!canSupervise) {
           Participant participant = participantRepositoryPort.findByUserIdAndChatId(principal.id(), chat.getId());
            if (participant == null || !participant.getActive()) {
                throw new ChatForbiddenException("You are not a participant of this chat");
            }
        }

        if (canSupervise && principal.has(PermissionEnum.TICKET_SUPERVISOR) ) {
            if(principal.specializationId() != ticket.specializationId() && ticket.specializationId() != null) {
                throw new ChatForbiddenException("you not be access this chat");
            }
        }

        return this.getMessages(chat, page);
    }

    private PageResult<MessageView> getMessages(Chat chat, PageQuery page) {
        PageResult<Message> messages = messageRepositoryPort.searchByChatId(chat.getId(), page);
        Map<UUID, List<ChatAttachmentInfo>> attachmentsMap = getAttachments(
            messages.items().stream().map(message -> message.getId()).toList()
        );

        return messages.map(
            message -> {
                Participant participant = participantRepositoryPort.findById(message.getSenderId());
                return new MessageView(
                    message,
                    message.getReplyToId() == null ? null : getReplyInfo(message.getReplyToId()),
                    new ChatParticipantInfo(
                        participant.getId(),
                        participant.getChatId(),
                        loadChatFindUserByIdPort.execute(participant.getUserId()),
                        participant.getJoinedAt()
                    ),
                    attachmentsMap.getOrDefault(message.getId(), List.of())
                );
            }
        );
    }

    private Map<UUID, List<ChatAttachmentInfo>> getAttachments(List<UUID> messagesId) {
        List<Attachment> attachments = attachmentRepositoryPort.findByMessageIds(messagesId);

        return attachments
            .stream()
            .map(
                attachment -> new ChatAttachmentInfo(
                    attachment.getId(),
                    attachment.getMessageId(),
                    loadChatFindFileByIdPort.execute(attachment.getFileId()),
                    attachment.getCreatedAt()
                )
            )
            .collect(Collectors.groupingBy(ChatAttachmentInfo::messageId));
    }
    
    private MessageInfo getReplyInfo(UUID id) {
        Message reply = messageRepositoryPort.findById(id);
        return new MessageInfo(
            reply.getId(),
            reply.getChatId(),
            reply.getContent(),
            reply.getReplyToId(),
            reply.getCreatedAt(),
            reply.getUpdatedAt()
        );
    }
}
