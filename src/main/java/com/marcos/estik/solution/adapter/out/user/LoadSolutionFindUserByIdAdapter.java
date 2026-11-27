package com.marcos.estik.solution.adapter.out.user;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.marcos.estik.solution.application.port.out.LoadSolutionFindUserByIdPort;
import com.marcos.estik.solution.application.view.SolutionUserInfo;
import com.marcos.estik.user.application.port.in.FindUserByIdPort;

import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class LoadSolutionFindUserByIdAdapter implements LoadSolutionFindUserByIdPort {

    private final FindUserByIdPort findUserByIdPort;
    private final SolutionUserMapper solutionUserMapper;
    @Override
    public SolutionUserInfo execute(UUID userId) {
        return solutionUserMapper.toInfo(findUserByIdPort.execute(userId));
    }
    
}
