package com.marcos.estik.departament.adapter.in.web.request;

import java.util.UUID;

public record CreateDepartamentRequest(
    UUID facilityId,
    String name,
    String code
) {
}
