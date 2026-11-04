package com.marcos.estik.files.application.usecases;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.marcos.estik.files.application.ports.in.SignFileUrlPort;
import com.marcos.estik.files.application.ports.out.SignedUrlPort;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class SignFileUrlUseCase implements SignFileUrlPort {

    private final SignedUrlPort signedUrlPort;

    @Override
    public String execute(UUID fileId) {
        return signedUrlPort.sign(fileId);
    }
}
