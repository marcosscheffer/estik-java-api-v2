package com.marcos.estik.ticket.adapter.out.persistence.assigneed;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.marcos.estik.ticket.application.ports.out.AssigneedRepositoryPort;
import com.marcos.estik.ticket.domain.Assigneed;

import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class AssigneedRepositoryAdapter implements AssigneedRepositoryPort {

    private final JpaAssigneedRepository jpaAssigneedRepository;
    private final AssigneedPersistenceMapper assigneedPersistenceMapper;
    
    @Override
    public Assigneed save(Assigneed assigneed) {
        AssigneedEntity assigneedEntity = assigneedPersistenceMapper.toEntity(assigneed);
        AssigneedEntity assigneedEntitySaved = jpaAssigneedRepository.save(assigneedEntity);
        return assigneedPersistenceMapper.toDomain(assigneedEntitySaved);
    }

    @Override
    public List<Assigneed> searchAssigneed(UUID ticketId) {
        return jpaAssigneedRepository.findByTicketId(ticketId)
            .stream()
            .map(assigneedPersistenceMapper::toDomain)
            .toList();
    }

    @Override
    public void delete(UUID id) {
        jpaAssigneedRepository.deleteById(id);
    }

    @Override
    public Boolean existsByTicketIdAndUserId(UUID ticketId, UUID userId) {
        return jpaAssigneedRepository.existsByTicketIdAndUserId(ticketId, userId);
    }
    
}
