package com.marcos.estik.chat.application.usecases;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.marcos.estik.chat.application.ports.in.DeactiveParticipantByUserIdAndChatIdPort;
import com.marcos.estik.chat.application.ports.out.ParticipantRepositoryPort;
import com.marcos.estik.chat.domain.Participant;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class DeactiveParticipantByUserIdAndChatIdUseCase implements DeactiveParticipantByUserIdAndChatIdPort {
    
    private final ParticipantRepositoryPort participantRepositoryPort;

    @Override
    public Participant execute(UUID userId, UUID chatId) {
        Participant participant = participantRepositoryPort.findByUserIdAndChatId(userId, chatId);
        participant.deactive();
        participantRepositoryPort.save(participant);
        return participant;
    }
}