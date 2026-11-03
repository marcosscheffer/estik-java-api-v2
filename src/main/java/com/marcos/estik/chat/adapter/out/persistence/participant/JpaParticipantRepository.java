package com.marcos.estik.chat.adapter.out.persistence.participant;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marcos.estik.chat.domain.Participant;

public interface JpaParticipantRepository extends JpaRepository<ParticipantEntity, UUID> {

    List<ParticipantEntity> findByChatId(UUID chatId);

    Boolean existsByUserId(UUID userId);

    Optional<Participant> findByUserId(UUID userId);
    
}
