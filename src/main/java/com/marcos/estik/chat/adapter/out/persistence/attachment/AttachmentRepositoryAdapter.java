package com.marcos.estik.chat.adapter.out.persistence.attachment;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.marcos.estik.chat.application.ports.out.AttachmentRepositoryPort;
import com.marcos.estik.chat.domain.Attachment;

import lombok.RequiredArgsConstructor;

@Component 
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
        List<AttachmentEntity> attachments = jpaAttachmentRepository.findByMessageIdIn(messageIds);
        return attachments
            .stream()
            .map(attachmentPersistenceMapper::toDomain)
            .toList();
    }
    @Override
    public List<Attachment> saveAll(Collection<Attachment> attachments) {
        List<AttachmentEntity> attachmentEntities = 
            attachments.stream().map(attachmentPersistenceMapper::toEntity).toList();
        List<AttachmentEntity> attachmentEntitiesSaved = jpaAttachmentRepository.saveAll(attachmentEntities);
        return attachmentEntitiesSaved.stream().map(attachmentPersistenceMapper::toDomain).toList();
    }    
}
