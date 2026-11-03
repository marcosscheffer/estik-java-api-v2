package com.marcos.estik.chat.application.usecases;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.marcos.estik.chat.application.command.FileUpload;
import com.marcos.estik.chat.application.command.SendMessageCommand;
import com.marcos.estik.chat.application.ports.in.SendMessagePort;
import com.marcos.estik.chat.application.ports.out.AttachmentRepositoryPort;
import com.marcos.estik.chat.application.ports.out.ChatRepositoryPort;
import com.marcos.estik.chat.application.ports.out.FileStoragePort;
import com.marcos.estik.chat.application.ports.out.FileTypeDetectorPort;
import com.marcos.estik.chat.application.ports.out.LoadChatFindUserByIdPort;
import com.marcos.estik.chat.application.ports.out.MessageRepositoryPort;
import com.marcos.estik.chat.application.ports.out.ParticipantRepositoryPort;
import com.marcos.estik.chat.application.view.ChatAttachmentInfo;
import com.marcos.estik.chat.application.view.ChatMessageInfo;
import com.marcos.estik.chat.application.view.ChatParticipantInfo;
import com.marcos.estik.chat.domain.Attachment;
import com.marcos.estik.chat.domain.Chat;
import com.marcos.estik.chat.domain.Message;
import com.marcos.estik.chat.domain.Participant;
import com.marcos.estik.chat.domain.exception.FileMimeTypeInvalidException;
import com.marcos.estik.security.AuthenticatedUser;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class SendMessageUseCase implements SendMessagePort {

    private static final Set<String> ALLOWED_TYPES = Set.of(
        "image/png", "image/jpeg", "image/webp", "application/pdf"
    );

    private final ParticipantRepositoryPort participantRepositoryPort;
    private final ChatRepositoryPort chatRepositoryPort;
    private final MessageRepositoryPort messageRepositoryPort;
    private final AttachmentRepositoryPort attachmentRepositoryPort;
    private final FileTypeDetectorPort fileTypeDetectorPort;
    private final LoadChatFindUserByIdPort loadChatFindUserByIdPort;
    private final FileStoragePort fileStoragePort;

    @Override
    public ChatMessageInfo execute(UUID chatId, SendMessageCommand command, AuthenticatedUser principal) {
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
            .map(file -> toAttachment(file, chatId, chatId))
            .toList();
        messageRepositoryPort.save(message);
        attachmentRepositoryPort.saveAll(attachments);

        return new ChatMessageInfo(
            message.getId(),
            chat.getId(),
            toParticipantInfo(participant),
            attachments.stream().map(this::toAttachmentInfo).toList(),
            message.getReplyToId(),
            message.getContent(),
            message.getCreatedAt(),
            message.getUpdatedAt()
        );
    }

    private ChatAttachmentInfo toAttachmentInfo(Attachment attachment) {
        return new ChatAttachmentInfo(
            attachment.getId(), 
            attachment.getMessageId(), 
            attachment.getFileName(), 
            attachment.getFileType(), 
            attachment.getFileSize(), 
            attachment.getFileUrl(), 
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
        String mimeType = fileTypeDetectorPort.detect(file.content(), file.fileName());
            if (!ALLOWED_TYPES.contains(mimeType)) {
                throw new FileMimeTypeInvalidException("File type invalid");
            }
            String path = fileStoragePort.store(chatId, file, mimeType);
            return Attachment.create(
                messageId,
                file.fileName(),
                mimeType,
                file.fileSize(),
                path
            );
    }
    
}
