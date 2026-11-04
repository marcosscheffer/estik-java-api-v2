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

    private final AttachmentPersistenceMapper attachmentPersistenceMapper;
    private final JpaAttachmentRepository jpaAttachmentRepository;
    
    @Override
    public Attachment save(Attachment attachment) {
        AttachmentEntity attachmentEntity = attachmentPersistenceMapper.toEntity(attachment);
        AttachmentEntity attachmentEntitySaved = jpaAttachmentRepository.save(attachmentEntity);
        return attachmentPersistenceMapper.toDomain(attachmentEntitySaved);
    }

    @Override
    public List<Attachment> findByMessageIds(Collection<UUID> messageIds) {
        return jpaAttachmentRepository.findByMessageIdIn(messageIds)
            .stream()
            .map(attachmentPersistenceMapper::toDomain)
            .toList();
    }
    
}
