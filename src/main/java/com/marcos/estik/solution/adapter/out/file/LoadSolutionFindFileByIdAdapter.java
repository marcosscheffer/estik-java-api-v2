package com.marcos.estik.solution.adapter.out.file;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.marcos.estik.files.application.ports.in.FindFileInfoByIdPort;
import com.marcos.estik.files.application.ports.in.SignFileUrlPort;
import com.marcos.estik.files.domain.File;
import com.marcos.estik.solution.application.port.out.LoadSolutionFindFileByidPort;
import com.marcos.estik.solution.application.view.SolutionFileInfo;

import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class LoadSolutionFindFileByIdAdapter implements LoadSolutionFindFileByidPort {
    
    private final FindFileInfoByIdPort findFileInfoByIdPort;
    private final SignFileUrlPort signFileUrlPort;
    private final SolutionFileMapper solutionFileMapper;

    @Override
    public SolutionFileInfo execute(UUID fileId) {
        File file = findFileInfoByIdPort.execute(fileId);
        return solutionFileMapper.toInfo(file, signFileUrlPort.execute(file.getId()));
    }
}
