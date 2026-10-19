package com.marcos.estik.facility.adapter.in.web;

import java.net.URI;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.marcos.estik.facility.adapter.in.web.mapper.FacilityWebMapper;
import com.marcos.estik.facility.adapter.in.web.request.CreateFacilityRequest;
import com.marcos.estik.facility.adapter.in.web.request.UpdateFacilityRequest;
import com.marcos.estik.facility.adapter.in.web.response.FacilityResponse;
import com.marcos.estik.facility.adapter.in.web.response.FacilitySummaryResponse;
import com.marcos.estik.facility.application.ports.in.CreateFacilityPort;
import com.marcos.estik.facility.application.ports.in.DeleteFacilityByIdPort;
import com.marcos.estik.facility.application.ports.in.FindFacilityByIdPort;
import com.marcos.estik.facility.application.ports.in.SearchFacilityByNamePort;
import com.marcos.estik.facility.application.ports.in.UpdateFacilityPort;
import com.marcos.estik.facility.domain.Facility;
import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.application.pagination.PageResult;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping("/facilities")
@RequiredArgsConstructor 
public class FacilityController {

    private final CreateFacilityPort createFacilityPort;
    private final SearchFacilityByNamePort searchFacilityByNamePort;
    private final FacilityWebMapper facilityWebMapper;
    private final FindFacilityByIdPort findFacilityByIdPort;
    private final UpdateFacilityPort updateFacilityPort;
    private final DeleteFacilityByIdPort deleteFacilityByIdPort;

    @PostMapping
    public ResponseEntity<FacilitySummaryResponse> newFacility(
        @RequestBody @Valid CreateFacilityRequest request,
        UriComponentsBuilder uriBuilder
    ) {
        Facility facility = createFacilityPort.execute(
            facilityWebMapper.toCreateFacilityCommand(request)
        );

        URI uri = uriBuilder
            .path("/facilities/{id}")
            .buildAndExpand(facility.getId())
            .toUri();

        return ResponseEntity.created(uri).body(
            facilityWebMapper.toSummaryResponse(facility)
        );
    }

    @GetMapping
    public ResponseEntity<PageResult<FacilitySummaryResponse>> getFacilities(
        @RequestParam(defaultValue = "") String q,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "20") int size
    ) {
        PageResult<Facility> facilities = searchFacilityByNamePort.execute(
            q, 
            new PageQuery(page, size)
        );

        return ResponseEntity.ok(facilities.map(facilityWebMapper::toSummaryResponse));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FacilityResponse> getFacility(
        @PathVariable UUID id
    ) {
        Facility facility = findFacilityByIdPort.execute(id);

        return ResponseEntity.ok(facilityWebMapper.toResponse(facility));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FacilityResponse> updateFacility(
        @PathVariable UUID id,
        @RequestBody @Valid UpdateFacilityRequest request
    ) {
        Facility facility = updateFacilityPort.execute(
            facilityWebMapper.toUpdateFacilityCommand(request),
            id
        );

        return ResponseEntity.ok(facilityWebMapper.toResponse(facility));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFacility(
        @PathVariable UUID id
    ) {
        deleteFacilityByIdPort.execute(id);
        return ResponseEntity.noContent().build();
    }
}
