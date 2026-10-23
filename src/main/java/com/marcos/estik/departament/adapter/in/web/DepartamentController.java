package com.marcos.estik.departament.adapter.in.web;

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

import com.marcos.estik.departament.adapter.in.web.mapper.DepartamentWebMapper;
import com.marcos.estik.departament.adapter.in.web.request.CreateDepartamentRequest;
import com.marcos.estik.departament.adapter.in.web.request.UpdateDepartamentRequest;
import com.marcos.estik.departament.adapter.in.web.response.DepartamentResponse;
import com.marcos.estik.departament.adapter.in.web.response.DepartamentSummaryResponse;
import com.marcos.estik.departament.application.command.SearchDepartamentCommand;
import com.marcos.estik.departament.application.ports.in.CreateDepartamentPort;
import com.marcos.estik.departament.application.ports.in.DeleteDepartamentByIdPort;
import com.marcos.estik.departament.application.ports.in.FindDepartamentByIdPort;
import com.marcos.estik.departament.application.ports.in.SearchDepartamentPort;
import com.marcos.estik.departament.application.ports.in.UpdateDepartamentPort;
import com.marcos.estik.departament.application.view.DepartamentView;
import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.application.pagination.PageResult;

import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping("/departaments")
@RequiredArgsConstructor 
public class DepartamentController {

    private final FindDepartamentByIdPort findDepartamentByIdPort;
    private final SearchDepartamentPort searchDepartamentPort;
    private final CreateDepartamentPort createDepartamentPort;
    private final UpdateDepartamentPort updateDepartamentPort;
    private final DepartamentWebMapper departamentWebMapper;
    private final DeleteDepartamentByIdPort deleteDepartamentByIdPort;
    
    @GetMapping("/{id}")
    public ResponseEntity<DepartamentResponse> getDepartament(
        @PathVariable UUID id
    ) {
        DepartamentView departament = findDepartamentByIdPort.execute(id);

        return ResponseEntity.ok(departamentWebMapper.toResponse(departament));
    }

    @GetMapping
    public ResponseEntity<PageResult<DepartamentSummaryResponse>> getDepartaments(
        @RequestParam(defaultValue = "") String name,
        @RequestParam(defaultValue = "") String code,
        @RequestParam(required = false) UUID facilityId,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "20") int size
    ) {
        PageResult<DepartamentView> departaments = searchDepartamentPort.execute(
            new SearchDepartamentCommand(
                name,
                code,
                facilityId,
                new PageQuery(page, size)
            )
        );

        return ResponseEntity.ok(departaments.map(departamentWebMapper::toSummaryResponse));
    }

    @PostMapping
    public ResponseEntity<DepartamentResponse> createDepartament(
        @RequestBody CreateDepartamentRequest request,
        UriComponentsBuilder uriBuilder
    ) {
        DepartamentView departament = createDepartamentPort.execute(
            departamentWebMapper.toCreateDepartamentCommand(request)
        );

        URI uri = uriBuilder
            .path("/departaments/{id}")
            .buildAndExpand(departament.departament().getId())
            .toUri();

        return ResponseEntity.created(uri).body(departamentWebMapper.toResponse(departament));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartamentResponse> updateDepartament(
        @PathVariable UUID id,
        @RequestBody UpdateDepartamentRequest request
    ) {
        DepartamentView departament = updateDepartamentPort.execute(
            id,
            departamentWebMapper.toUpdateDepartamentCommand(request)
        );

        return ResponseEntity.ok(departamentWebMapper.toResponse(departament));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartament(
        @PathVariable UUID id
    ) {
        deleteDepartamentByIdPort.execute(id);
        return ResponseEntity.noContent().build();
    }
}
