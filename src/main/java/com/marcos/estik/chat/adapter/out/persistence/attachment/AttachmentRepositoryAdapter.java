package com.marcos.estik.chat.adapter.out.persistence.attachment;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import com.marcos.estik.chat.application.ports.out.AttachmentRepositoryPort;
import com.marcos.estik.chat.domain.Attachment;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor 
public class AttachmentRepositoryAdapter implements AttachmentRepositoryPort {

    private final JpaAttachmentRepository jpaAttachmentRepository;
    private final AttachmentPersistenceMapper attachmentPersistenceMapper;

    @Override
    public Attachment save(Attachment attachment) {
        AttachmentEntity attachmentEntity = attachmentPersistenceMapper.toEntity(attachment);
        AttachmentEntity savedAttachmentEntity = jpaAttachmentRepository.save(attachmentEntity);
        return attachmentPersistenceMapper.toDomain(savedAttachmentEntity);
    }
    @Override
    public List<Attachment> findByMessageIds(Collection<UUID> messageIds) {
        List<AttachmentEntity> attachments = jpaAttachmentRepository.findByMessageIds(messageIds);
        return attachments
        .stream()
            .map(attachmentPersistenceMapper::toDomain)
            .toList();
    }    
}
