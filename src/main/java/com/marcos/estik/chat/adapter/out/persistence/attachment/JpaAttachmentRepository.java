package com.marcos.estik.chat.adapter.out.persistence.attachment;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaAttachmentRepository extends JpaRepository<AttachmentEntity, UUID> {

    List<AttachmentEntity> findByMessageIds(Collection<UUID> messageIds);
    
}
