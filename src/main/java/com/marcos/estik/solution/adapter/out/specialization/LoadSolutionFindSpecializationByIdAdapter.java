package com.marcos.estik.solution.adapter.out.specialization;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.marcos.estik.solution.application.port.out.LoadSolutionFindSpecializationByIdPort;
import com.marcos.estik.solution.application.view.SolutionSpecializationInfo;
import com.marcos.estik.specialization.application.ports.in.FindSpecializationByIdPort;

import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class LoadSolutionFindSpecializationByIdAdapter implements LoadSolutionFindSpecializationByIdPort {
    
    private final FindSpecializationByIdPort findSpecializationByIdPort;
    private final SolutionSpecializationMapper solutionSpecializationMapper;
    
    @Override
    public SolutionSpecializationInfo execute(UUID specializationId) {
        return solutionSpecializationMapper.toInfo(findSpecializationByIdPort.execute(specializationId));
    }
    
}
