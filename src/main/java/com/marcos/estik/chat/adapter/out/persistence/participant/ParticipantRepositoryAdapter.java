package com.marcos.estik.chat.adapter.out.persistence.participant;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.marcos.estik.chat.application.ports.out.ParticipantRepositoryPort;
import com.marcos.estik.chat.domain.Participant;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class ParticipantRepositoryAdapter implements ParticipantRepositoryPort {

    private final ParticipantPersistenceMapper participantPersistenceMapper;
    private final JpaParticipantRepository jpaParticipantRepository;

    @Override
    public Participant save(Participant participant) {
        ParticipantEntity participantEntity = participantPersistenceMapper.toEntity(participant);
        ParticipantEntity savedParticipantEntity = jpaParticipantRepository.save(participantEntity);
        return participantPersistenceMapper.toDomain(savedParticipantEntity);
    }

    @Override
    public List<Participant> findByChatId(UUID chatId) {
        List<ParticipantEntity> participants = jpaParticipantRepository.findByChatId(chatId);
        return participants.stream()
            .map(participantPersistenceMapper::toDomain)
            .toList();
    }

    @Override
    public Boolean existsByUserIdAndChatId(UUID userId, UUID chatId) {
        return jpaParticipantRepository.existsByUserId(userId);
    }

    @Override
    public Participant findByUserIdAndChatId(UUID userId, UUID chatId) {
        return jpaParticipantRepository.findByUserId(userId).orElseThrow(EntityNotFoundException::new);
    }

}
