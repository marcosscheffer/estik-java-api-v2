package com.marcos.estik.computer.adapter.in.web;

import java.net.URI;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.marcos.estik.computer.adapter.in.web.mapper.ComputerWebMapper;
import com.marcos.estik.computer.adapter.in.web.request.CreateComputerRequest;
import com.marcos.estik.computer.adapter.in.web.request.UpdateComputerRequest;
import com.marcos.estik.computer.adapter.in.web.response.ComputerResponse;
import com.marcos.estik.computer.adapter.in.web.response.ComputerSummaryResponse;
import com.marcos.estik.computer.application.command.SearchComputerCommand;
import com.marcos.estik.computer.application.ports.in.CreateComputerPort;
import com.marcos.estik.computer.application.ports.in.FindComputerByIdPort;
import com.marcos.estik.computer.application.ports.in.SearchComputerPort;
import com.marcos.estik.computer.application.ports.in.UpdateComputerPort;
import com.marcos.estik.computer.domain.Computer;
import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.application.pagination.PageResult;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping("/computers")
@RequiredArgsConstructor 
public class ComputerController {

    private final SearchComputerPort searchComputerPort;
    private final FindComputerByIdPort findComputerByIdPort;
    private final CreateComputerPort createComputerPort;
    private final ComputerWebMapper computerWebMapper;
    private final UpdateComputerPort updateComputerPort;

    @GetMapping
    public ResponseEntity<PageResult<ComputerSummaryResponse>> getComputers(
        @RequestParam(required = false) UUID facilityId,
        @RequestParam(required = false) UUID departamentId,
        @RequestParam(defaultValue = "") String name,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "20") int size
    ) {
        PageResult<Computer> computers = searchComputerPort.execute(
            new SearchComputerCommand(
                name,
                facilityId,
                departamentId,
                new PageQuery(page, size)
            )
        );

        return ResponseEntity.ok(computers.map(computerWebMapper::toSummaryResponse));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComputerResponse> getComputer(
        @PathVariable UUID id
    ) {
        Computer computer = findComputerByIdPort.execute(id);

        return ResponseEntity.ok(computerWebMapper.toResponse(computer));
    }

    @PostMapping
    public ResponseEntity<ComputerResponse> createComputer(
        @RequestBody @Valid CreateComputerRequest request,
        UriComponentsBuilder uriBuilder
    ) {
        Computer computer = createComputerPort.execute(computerWebMapper.toCreateComputerCommand(request));
        
        URI uri = uriBuilder
            .path("/computer/{id}")
            .buildAndExpand(computer.getId())
            .toUri();

        return ResponseEntity.created(uri).body(computerWebMapper.toResponse(computer));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ComputerResponse> updateComputer(
        @PathVariable UUID id,
        @RequestBody @Valid  UpdateComputerRequest request
    ) {
        Computer computer = updateComputerPort.execute(
            computerWebMapper.toUpdateComputerCommand(request),
            id
        );

        return ResponseEntity.ok(computerWebMapper.toResponse(computer));
    }


}
