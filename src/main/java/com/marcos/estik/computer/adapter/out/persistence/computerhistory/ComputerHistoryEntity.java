package com.marcos.estik.computer.adapter.out.persistence.computerhistory;

import java.time.Instant;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import com.marcos.estik.computer.domain.DirectionEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity 
@Table(name = "computer_histories")
@Getter 
@Setter 
public class ComputerHistoryEntity {
    @Id 
    private UUID id;
    private String serviceDescription;

    @Enumerated(EnumType.STRING)
    private DirectionEnum direction;

    @Column(name = "computer_id") 
    private UUID computerId;

    @Column(name = "departament_id")
    private UUID departamentId;

    @CreationTimestamp
    private Instant createdAt;

}
