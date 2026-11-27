package com.marcos.estik.solution.adapter.in.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import com.marcos.estik.security.AuthenticatedUser;
import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.application.pagination.PageResult;
import com.marcos.estik.shared.domain.file.FileUpload;
import com.marcos.estik.solution.adapter.in.web.mapper.SolutionResponseWebMapper;
import com.marcos.estik.solution.adapter.in.web.request.CreateSolutionResponseRequest;
import com.marcos.estik.solution.adapter.in.web.request.UpdateSolutionResponseRequest;
import com.marcos.estik.solution.adapter.in.web.response.SolutionResponseResponse;
import com.marcos.estik.solution.adapter.in.web.response.SolutionResponseSummaryResponse;
import com.marcos.estik.solution.application.command.CreateSolutionResponseCommand;
import com.marcos.estik.solution.application.port.in.CreateSolutionResponsePort;
import com.marcos.estik.solution.application.port.in.DeleteSolutionResponseByIdPort;
import com.marcos.estik.solution.application.port.in.FindSolutionResponseByIdPort;
import com.marcos.estik.solution.application.port.in.SearchSolutionResponsePort;
import com.marcos.estik.solution.application.port.in.UpdateSolutionResponsePort;
import com.marcos.estik.solution.application.view.SolutionResponseView;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URI;
import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/solutions/responses")
@RequiredArgsConstructor 
public class SolutionResponseController {

    private final FindSolutionResponseByIdPort findSolutionResponseByIdPort;
    private final SearchSolutionResponsePort searchSolutionResponsePort;
    private final CreateSolutionResponsePort createSolutionResponsePort;
    private final UpdateSolutionResponsePort updateSolutionResponsePort;
    private final DeleteSolutionResponseByIdPort deleteSolutionResponseByIdPort;
    private final SolutionResponseWebMapper solutionResponseWebMapper;

    @GetMapping("/{id}")
    public ResponseEntity<SolutionResponseResponse> findSolutionResponse(
        @PathVariable UUID id
    ) {
        SolutionResponseView solutionResponse = findSolutionResponseByIdPort.execute(id);
        return ResponseEntity.ok(solutionResponseWebMapper.toResponse(solutionResponse));
    }

    @GetMapping
    public ResponseEntity<PageResult<SolutionResponseSummaryResponse>> searchSolutionResponse(
        @RequestParam(required = true) UUID solutionPostId,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "20") int size
    ) {
        PageResult<SolutionResponseView> solutionResponses = searchSolutionResponsePort.execute(solutionPostId, new PageQuery(page, size));
        return ResponseEntity.ok(solutionResponses.map(solutionResponseWebMapper::toSummaryResponse));
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<SolutionResponseResponse> createSolutionResponse(
        @RequestPart("request") @Valid CreateSolutionResponseRequest request,
        @RequestPart(value = "file", required = false) MultipartFile file,
        @AuthenticationPrincipal AuthenticatedUser principal,
        UriComponentsBuilder uriBuilder 
    ) {
        SolutionResponseView solutionResponse = createSolutionResponsePort.execute(
            new CreateSolutionResponseCommand(
                request.solutionPostId(),
                request.message(),
                file == null ? null : toFileUpload(file)
            ),
            principal
        );

        URI uri = uriBuilder
            .path("/solutions/responses/{id}")
            .buildAndExpand(solutionResponse.solutionResponse().getId())
            .toUri();

        return ResponseEntity.created(uri).body(solutionResponseWebMapper.toResponse(solutionResponse));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SolutionResponseResponse> updateSolutionResponse(
        @PathVariable UUID id,
        @RequestBody @Valid UpdateSolutionResponseRequest command,
        @AuthenticationPrincipal AuthenticatedUser principal
    ) {
        SolutionResponseView solutionResponse = updateSolutionResponsePort.execute(
            id, 
            command.message(), 
            principal
        );

        return ResponseEntity.ok(solutionResponseWebMapper.toResponse(solutionResponse));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSolutionResponse(
        @PathVariable UUID id,
        @AuthenticationPrincipal AuthenticatedUser principal
    ) {
        deleteSolutionResponseByIdPort.execute(id, principal);
        return ResponseEntity.noContent().build();
    }
    
    private FileUpload toFileUpload(MultipartFile file) {
        if (file == null || file.isEmpty()) return null;
        try {
            return new FileUpload(
                file.getOriginalFilename(),
                file.getSize(),
                file.getBytes()
            );
        } catch (IOException exception) {
            throw new UncheckedIOException("Failed to read uploaded file", exception);
        }
    }
}
