package com.marcos.estik.ticket.adapter.out.persistence.forwarded;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JpaForwardedRepository extends JpaRepository<ForwardedEntity, UUID> {

    List<ForwardedEntity> findByTicketId(UUID ticketId);

    Boolean existsByTicketIdAndUserId(UUID ticketId, UUID userId);

    @Modifying
    @Query("DELETE FROM ForwardedEntity f WHERE f.ticketId = :ticketId")
    void deleteAllByTicketId(@Param("ticketId") UUID ticketId);
    
}
