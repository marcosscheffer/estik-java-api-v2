package com.marcos.estik.files.adapter.in.web;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.UUID;

import org.springframework.http.CacheControl;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.marcos.estik.files.application.ports.in.LoadFilePort;
import com.marcos.estik.files.application.view.FileContentView;

import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping("/files")
@RequiredArgsConstructor 
public class FileController {

    private final LoadFilePort loadFilePort;
    
    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getFile(
        @PathVariable UUID id,
        @RequestParam Long expires,
        @RequestParam String signature
    ) {
        FileContentView file = loadFilePort.execute(id, expires, signature);

        ContentDisposition disposition = ContentDisposition.inline()
            .filename(file.fileName(), StandardCharsets.UTF_8)
            .build();
        
        return ResponseEntity.ok()
            .contentType(MediaType.parseMediaType(file.fileType()))
            .header(HttpHeaders.CONTENT_DISPOSITION, disposition.toString(), disposition.toString())
            .header("X-Content-Type-Options", "nosniff")
            .cacheControl(CacheControl.maxAge(Duration.ofHours(1)).cachePrivate())
            .body(file.content());
    }
}
