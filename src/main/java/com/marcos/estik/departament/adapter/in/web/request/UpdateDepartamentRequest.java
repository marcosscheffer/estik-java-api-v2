package com.marcos.estik.departament.adapter.in.web.request;

import java.util.UUID;

public record UpdateDepartamentRequest(
    UUID facilityId,
    String name,
    String code
) {
    
}
