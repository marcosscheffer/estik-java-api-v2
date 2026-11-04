package com.marcos.estik.chat.application.usecases;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marcos.estik.chat.application.command.SendMessageCommand;
import com.marcos.estik.chat.application.ports.in.SendMessagePort;
import com.marcos.estik.chat.application.ports.out.AttachmentRepositoryPort;
import com.marcos.estik.chat.application.ports.out.ChatRepositoryPort;
import com.marcos.estik.chat.application.ports.out.LoadChatFindFileByIdPort;
import com.marcos.estik.chat.application.ports.out.LoadChatFindUserByIdPort;
import com.marcos.estik.chat.application.ports.out.LoadChatStoreFilePort;
import com.marcos.estik.chat.application.ports.out.MessagePushPort;
import com.marcos.estik.chat.application.ports.out.MessageRepositoryPort;
import com.marcos.estik.chat.application.ports.out.ParticipantRepositoryPort;
import com.marcos.estik.chat.application.view.ChatAttachmentInfo;
import com.marcos.estik.chat.application.view.ChatFileInfo;
import com.marcos.estik.chat.application.view.MessageView;
import com.marcos.estik.chat.application.view.ChatParticipantInfo;
import com.marcos.estik.chat.application.view.MessageInfo;
import com.marcos.estik.chat.domain.Attachment;
import com.marcos.estik.chat.domain.Chat;
import com.marcos.estik.chat.domain.Message;
import com.marcos.estik.chat.domain.Participant;
import com.marcos.estik.security.AuthenticatedUser;
import com.marcos.estik.shared.domain.file.FileUpload;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class SendMessageUseCase implements SendMessagePort {

    private final ParticipantRepositoryPort participantRepositoryPort;
    private final ChatRepositoryPort chatRepositoryPort;
    private final MessageRepositoryPort messageRepositoryPort;
    private final AttachmentRepositoryPort attachmentRepositoryPort;
    private final LoadChatFindUserByIdPort loadChatFindUserByIdPort;
    private final LoadChatStoreFilePort loadChatStoreFilePort;
    private final LoadChatFindFileByIdPort loadChatFindFileByIdPort;
    private final MessagePushPort messagePushPort;

    @Override
    @Transactional()
    public MessageView execute(UUID chatId, SendMessageCommand command, AuthenticatedUser principal) {
        Chat chat = chatRepositoryPort.findById(chatId);
        Participant participant = participantRepositoryPort.findByUserIdAndChatId(principal.id(), chat.getId());
        Message message = Message.create(
            chat.getId(), 
            participant.getId(),
            command.replyToId(), 
            command.content()
        );

        List<Attachment> attachments = command.files()
            .stream()
            .map(file -> toAttachment(file, message.getId(), chat.getId()))
            .toList();
        messageRepositoryPort.save(message);
        attachments.forEach(attachment -> attachmentRepositoryPort.save(attachment));

        MessageView messageView = new MessageView(
            message,
            message.getReplyToId() == null ? null : getReplyInfo(message.getReplyToId()),
            toParticipantInfo(participant),
            attachments.stream().map(this::toAttachmentInfo).toList()
        );

        messagePushPort.execute(messageView);
        return messageView;
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

    private ChatAttachmentInfo toAttachmentInfo(Attachment attachment) {
        return new ChatAttachmentInfo(
            attachment.getId(), 
            attachment.getMessageId(), 
            loadChatFindFileByIdPort.execute(attachment.getFileId()),
            attachment.getCreatedAt()
        );
    }

    private ChatParticipantInfo toParticipantInfo(Participant participant) {
        return new ChatParticipantInfo(
            participant.getId(),
            participant.getChatId(),
            loadChatFindUserByIdPort.execute(participant.getUserId()),
            participant.getJoinedAt()
        );
    }

    private Attachment toAttachment(FileUpload file, UUID messageId, UUID chatId) {
        ChatFileInfo fileInfo = loadChatStoreFilePort.execute("chat/" + chatId, file);
            return Attachment.create(
                messageId,
                fileInfo.id()
            );
    }
}
