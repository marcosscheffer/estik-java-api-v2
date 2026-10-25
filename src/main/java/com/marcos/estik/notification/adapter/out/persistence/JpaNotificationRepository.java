package com.marcos.estik.notification.adapter.out.persistence;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;



public interface JpaNotificationRepository extends JpaRepository<NotificationEntity, UUID> {

    List<NotificationEntity> findByRecipientIdAndRead(UUID recipientId, boolean read);

    Page<NotificationEntity> findByRecipientId(UUID recipientId, Pageable pageable);
    
}
