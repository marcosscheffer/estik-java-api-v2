package com.marcos.estik.files.application.ports.out;

import java.util.UUID;

public interface SignedUrlPort {
    
    String sign(UUID fileId);

    Boolean isValid(UUID fileId, Long expires, String signature);

}
