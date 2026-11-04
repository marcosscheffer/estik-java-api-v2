package com.marcos.estik.chat.adapter.out.persistence.participant;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marcos.estik.chat.domain.Participant;

public interface JpaParticipantRepository extends JpaRepository<ParticipantEntity, UUID> {

    Optional<ParticipantEntity> findByUserIdAndChatId(UUID userId, UUID chatId);

    Boolean existsByUserIdAndChatId(UUID userId, UUID chatId);

    Optional<Participant> findByUserId(UUID userId);

    List<ParticipantEntity> findByChatId(UUID chatId);
    
}
