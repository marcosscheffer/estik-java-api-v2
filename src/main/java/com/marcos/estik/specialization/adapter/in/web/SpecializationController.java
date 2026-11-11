package com.marcos.estik.specialization.adapter.in.web;

import java.net.URI;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.application.pagination.PageResult;
import com.marcos.estik.specialization.adapter.in.web.mapper.SpecializationWebMapper;
import com.marcos.estik.specialization.adapter.in.web.request.CreateSpecializationRequest;
import com.marcos.estik.specialization.adapter.in.web.response.SpecializationResponse;
import com.marcos.estik.specialization.application.ports.in.CreateSpecializationPort;
import com.marcos.estik.specialization.application.ports.in.DeleteSpecializationPort;
import com.marcos.estik.specialization.application.ports.in.FindSpecializationByIdPort;
import com.marcos.estik.specialization.application.ports.in.SearchSpecializationPort;
import com.marcos.estik.specialization.domain.Specialization;

import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping("/specializations")
@RequiredArgsConstructor 
public class SpecializationController {

    private final SearchSpecializationPort searchSpecializationPort;
    private final FindSpecializationByIdPort findSpecializationByIdPort;
    private final CreateSpecializationPort createSpecializationPort;
    private final DeleteSpecializationPort deleteSpecializationPort;
    private final SpecializationWebMapper specializationWebMapper;

    @GetMapping
    public ResponseEntity<PageResult<SpecializationResponse>> getSpecializations(
        @RequestParam(defaultValue = "") String name,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "20") int size
    ) {
        PageResult<Specialization> specializations = searchSpecializationPort.execute(
            name, 
            new PageQuery(page, size)
        );
        return ResponseEntity.ok(specializations.map(specializationWebMapper::toResponse));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpecializationResponse> getSpecialization(
        @PathVariable UUID id
    ) {
        Specialization specialization = findSpecializationByIdPort.execute(id);
        return ResponseEntity.ok(specializationWebMapper.toResponse(specialization));
    }

    @PostMapping
    public ResponseEntity<SpecializationResponse> createSpecialization(
        @RequestBody CreateSpecializationRequest request,
        UriComponentsBuilder uriBuilder
    ) {
        Specialization specialization = createSpecializationPort.execute(request.name());
        URI uri = uriBuilder
            .path("/{id}")
            .buildAndExpand(specialization.getId())
            .toUri();
        return ResponseEntity.created(uri).body(specializationWebMapper.toResponse(specialization));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpecialization(
        @PathVariable UUID id
    ) {
        deleteSpecializationPort.execute(id);
        return ResponseEntity.noContent().build();
    }
}
