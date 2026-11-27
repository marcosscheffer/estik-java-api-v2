package com.marcos.estik.solution.application.usecases;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marcos.estik.security.AuthenticatedUser;
import com.marcos.estik.shared.domain.user.PermissionEnum;
import com.marcos.estik.solution.application.port.in.DeleteSolutionResponseByIdPort;
import com.marcos.estik.solution.application.port.out.SolutionResponseRepositoryPort;
import com.marcos.estik.solution.domain.SolutionResponse;
import com.marcos.estik.solution.domain.exception.SolutionForbiddenException;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class DeleteSolutionResponseByIdUseCase implements DeleteSolutionResponseByIdPort {
    
    private final SolutionResponseRepositoryPort solutionResponseRepositoryPort;

    @Override
    @Transactional 
    public void execute(UUID id, AuthenticatedUser principal) {
        SolutionResponse solutionResponse = solutionResponseRepositoryPort.findById(id);
        if (!solutionResponse.getAuthorId().equals(principal.id()) && !principal.has(PermissionEnum.SOLUTION_MANAGE)) {
            throw new SolutionForbiddenException("The post not belong to you");
        }

        solutionResponseRepositoryPort.deleteById(id);
    }
}
