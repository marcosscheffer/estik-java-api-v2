package com.marcos.estik.solution.adapter.in.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import com.marcos.estik.security.AuthenticatedUser;
import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.application.pagination.PageResult;
import com.marcos.estik.shared.domain.file.FileUpload;
import com.marcos.estik.solution.adapter.in.web.mapper.SolutionPostWebMapper;
import com.marcos.estik.solution.adapter.in.web.request.CreateSolutionPostRequest;
import com.marcos.estik.solution.adapter.in.web.request.UpdateSolutionPostRequest;
import com.marcos.estik.solution.adapter.in.web.response.SolutionPostResponse;
import com.marcos.estik.solution.adapter.in.web.response.SolutionPostSummaryResponse;
import com.marcos.estik.solution.application.command.CreateSolutionPostCommand;
import com.marcos.estik.solution.application.command.UpdateSolutionPostCommand;
import com.marcos.estik.solution.application.port.in.CreateSolutionPostPort;
import com.marcos.estik.solution.application.port.in.DeleteSolutionPostByIdPort;
import com.marcos.estik.solution.application.port.in.FindSolutionPostByIdPort;
import com.marcos.estik.solution.application.port.in.SearchSolutionPostPort;
import com.marcos.estik.solution.application.port.in.UpdateSolutionPostPort;
import com.marcos.estik.solution.application.view.SolutionPostView;

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
@RequestMapping("/solutions/posts")
@RequiredArgsConstructor 
public class SolutionPostController {

    private final FindSolutionPostByIdPort findSolutionPostByIdPort;
    private final SearchSolutionPostPort searchSolutionPostPort;
    private final CreateSolutionPostPort createSolutionPostPort;
    private final UpdateSolutionPostPort updateSolutionPostPort;
    private final DeleteSolutionPostByIdPort deleteSolutionPostByIdPort;
    private final SolutionPostWebMapper solutionPostWebMapper;

    @GetMapping("/{id}")
    public ResponseEntity<SolutionPostResponse> findSolutionPost(
        @PathVariable UUID id
    ) {
        SolutionPostView solutionPost = findSolutionPostByIdPort.execute(id);
        return ResponseEntity.ok(solutionPostWebMapper.toResponse(solutionPost));
    }

    @GetMapping
    public ResponseEntity<PageResult<SolutionPostSummaryResponse>> searchSolutionPosts(
        @RequestParam(defaultValue = "") String title,
        @RequestParam(required = false) UUID specializationId,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "20") int size
    ) {
        PageResult<SolutionPostView> solutionPosts = searchSolutionPostPort.execute(
            title, 
            specializationId, 
            new PageQuery(page, size)
        );

        return ResponseEntity.ok(solutionPosts.map(solutionPostWebMapper::toSummaryResponse));
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<SolutionPostResponse> createSolutionPost(
        @RequestPart("request") @Valid CreateSolutionPostRequest request,
        @RequestPart(value = "file", required = false) MultipartFile file,
        @AuthenticationPrincipal AuthenticatedUser principal,
        UriComponentsBuilder uriBuilder
    ) {
        SolutionPostView solutionPost = createSolutionPostPort.execute(
            new CreateSolutionPostCommand(
                request.title(),
                request.description(),
                file == null ? null : toFileUpload(file)
            ), 
            principal
        );

        URI uri = uriBuilder
            .path("/solutions/posts/{id}")
            .buildAndExpand(solutionPost.solutionPost().getId())
            .toUri();

        return ResponseEntity.created(uri).body(solutionPostWebMapper.toResponse(solutionPost));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SolutionPostResponse> updateSolutionPost(
        @PathVariable UUID id,
        @RequestBody @Valid UpdateSolutionPostRequest request,
        @AuthenticationPrincipal AuthenticatedUser principal
    ) {
        SolutionPostView solutionPost = updateSolutionPostPort.execute(
            id, 
            new UpdateSolutionPostCommand(
                request.title(),
                request.description()
            ), 
            principal
        );

        return ResponseEntity.ok(solutionPostWebMapper.toResponse(solutionPost));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSolutionPost(
        @PathVariable UUID id,
        @AuthenticationPrincipal AuthenticatedUser principal
    ) {
        deleteSolutionPostByIdPort.execute(id, principal);
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
