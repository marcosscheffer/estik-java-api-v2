package com.marcos.estik.chat.adapter.out.persistence.attachment;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaAttachmentRepository extends JpaRepository<AttachmentEntity, UUID> {
    
}
