package com.marcos.estik.notification.adapter.out.persistence;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.marcos.estik.notification.application.ports.out.NotificationRepositoryPort;
import com.marcos.estik.notification.domain.Notification;
import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.application.pagination.PageResult;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class NotificationRepositoryAdapter implements NotificationRepositoryPort {

    private final JpaNotificationRepository jpaNotificationRepository;
    private final NotificationPersistenceMapper notificationPersistenceMapper;
    
    @Override
    public Notification save(Notification notification) {
        NotificationEntity notificationEntity = notificationPersistenceMapper.toEntity(notification);
        NotificationEntity notificationSaved = jpaNotificationRepository.save(notificationEntity);
        return notificationPersistenceMapper.toDomain(notificationSaved);
    }

    @Override
    public List<Notification> findByRecipientIdAndNotRead(UUID recipientId) {
        return jpaNotificationRepository.findByRecipientIdAndRead(recipientId, false)
            .stream().map(notificationPersistenceMapper::toDomain).toList();
    }

    @Override
    public PageResult<Notification> searchNotification(UUID recipientId, PageQuery page) {
        Page<NotificationEntity> notifications = jpaNotificationRepository.findByRecipientId(
                recipientId, 
                PageRequest.of(page.page(), page.size(),
                Sort.by(Sort.Direction.DESC, "createdAt")
            )
        );
        
        return new PageResult<Notification>(
            notifications.getContent().stream().map(notificationPersistenceMapper::toDomain).toList(),
            notifications.getNumber(),
            notifications.getSize(),
            notifications.getTotalElements(),
            notifications.getTotalPages()
        );
    }

    @Override
    public List<Notification> saveAll(List<Notification> notifications) {
        List<NotificationEntity> notificationsEntity = notifications.stream()
            .map(notificationPersistenceMapper::toEntity).toList();
        List<NotificationEntity> notificationsSaved = jpaNotificationRepository.saveAll(notificationsEntity);
        return notificationsSaved.stream().map(notificationPersistenceMapper::toDomain).toList();
    }

    @Override
    public Notification findById(UUID notificationId) {
        return notificationPersistenceMapper.toDomain(jpaNotificationRepository.findById(notificationId)
            .orElseThrow(
                EntityNotFoundException::new 
            )
        );
    }
}