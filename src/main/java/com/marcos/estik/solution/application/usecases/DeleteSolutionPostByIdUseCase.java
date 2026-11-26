package com.marcos.estik.solution.application.usecases;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.marcos.estik.security.AuthenticatedUser;
import com.marcos.estik.shared.domain.user.PermissionEnum;
import com.marcos.estik.solution.application.port.in.DeleteSolutionPostByIdPort;
import com.marcos.estik.solution.application.port.out.SolutionPostRepositoryPort;
import com.marcos.estik.solution.domain.SolutionPost;
import com.marcos.estik.solution.domain.exception.SolutionForbiddenException;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class DeleteSolutionPostByIdUseCase implements DeleteSolutionPostByIdPort {
    
    private final SolutionPostRepositoryPort solutionPostRepositoryPort;

    @Override
    public void execute(UUID id, AuthenticatedUser principal) {
        SolutionPost solutionPost = solutionPostRepositoryPort.findById(id);

        if (!solutionPost.getAuthorId().equals(principal.id()) && !principal.has(PermissionEnum.SOLUTION_MANAGER)) {
            throw new SolutionForbiddenException("The post not belong to you");
        }
        solutionPostRepositoryPort.deleteById(id);
    }
}
