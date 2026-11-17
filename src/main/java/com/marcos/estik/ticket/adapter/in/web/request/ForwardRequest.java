package com.marcos.estik.ticket.adapter.in.web.request;

import java.util.List;
import java.util.UUID;

import com.marcos.estik.ticket.domain.enums.PriorityEnum;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ForwardRequest(
    @NotEmpty
    @Size(min = 1, max = 10)
    List<@NotNull UUID> usersId,
    @NotNull 
    PriorityEnum priority

) {
    
}
