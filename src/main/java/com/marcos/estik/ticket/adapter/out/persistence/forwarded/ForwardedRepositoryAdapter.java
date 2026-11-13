package com.marcos.estik.ticket.adapter.out.persistence.forwarded;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.marcos.estik.ticket.application.ports.out.ForwardedRepositoryPort;
import com.marcos.estik.ticket.domain.Forwarded;

import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class ForwardedRepositoryAdapter implements ForwardedRepositoryPort {
    
    private final JpaForwardedRepository jpaForwardedRepository;
    private final ForwardedPersistenceMapper forwardedPersistenceMapper;

    @Override
    public Forwarded save(Forwarded forwarded) {
        ForwardedEntity forwardedEntity = forwardedPersistenceMapper.toEntity(forwarded);
        ForwardedEntity forwardedEntitySaved = jpaForwardedRepository.save(forwardedEntity);
        return forwardedPersistenceMapper.toDomain(forwardedEntitySaved);
    }

    @Override
    public List<Forwarded> searchForwarded(UUID ticketId) {
        return jpaForwardedRepository.findByTicketId(ticketId)
        .stream()
        .map(forwardedPersistenceMapper::toDomain)
        .toList();
    }

    @Override
    public void delete(UUID id) {
        jpaForwardedRepository.deleteById(id);
    }

    @Override
    public Boolean existsByTicketIdAndUserId(UUID ticketId, UUID userId) {
        return jpaForwardedRepository.existsByTicketIdAndUserId(ticketId, userId);
    }
    
}
