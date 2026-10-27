package com.marcos.estik.computerhistory.adapter.out.computer;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.marcos.estik.computer.application.ports.in.FindComputerByIdPort;
import com.marcos.estik.computerhistory.application.ports.out.LoadComputerHistoryFindComputerByIdPort;
import com.marcos.estik.computerhistory.application.view.ComputerHistoryComputerInfo;

import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class LoadComputerHistoryFindComputerByIdAdapter implements LoadComputerHistoryFindComputerByIdPort {

    private final FindComputerByIdPort findComputerByIdPort;
    private final ComputerHistoryComputerMapper computerHistoryComputerMapper;

    @Override
    public ComputerHistoryComputerInfo execute(UUID id) {
        return computerHistoryComputerMapper.toComputerInfo(findComputerByIdPort.execute(id));
    }
}
