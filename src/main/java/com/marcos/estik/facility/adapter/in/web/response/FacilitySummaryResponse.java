package com.marcos.estik.facility.adapter.in.web.response;

import java.util.UUID;

public record FacilitySummaryResponse(
    UUID id,
    String name,
    String code
) {
}
