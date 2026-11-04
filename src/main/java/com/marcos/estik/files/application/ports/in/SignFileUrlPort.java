package com.marcos.estik.files.application.ports.in;

import java.util.UUID;

public interface SignFileUrlPort {
    
    String execute(UUID fileId);

}
