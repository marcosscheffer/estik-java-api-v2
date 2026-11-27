package com.marcos.estik.solution.adapter.out.file;

import org.springframework.stereotype.Component;

import com.marcos.estik.files.application.ports.in.SignFileUrlPort;
import com.marcos.estik.files.application.ports.in.StoreFilePort;
import com.marcos.estik.files.domain.File;
import com.marcos.estik.shared.domain.file.FileUpload;
import com.marcos.estik.solution.application.port.out.LoadSolutionStoreFilePort;
import com.marcos.estik.solution.application.view.SolutionFileInfo;

import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class LoadSolutionStoreFileAdapter implements LoadSolutionStoreFilePort {
    
    private final StoreFilePort storeFilePort;
    private final SignFileUrlPort signFileUrlPort;
    private final SolutionFileMapper solutionFileMapper;
    
    @Override
    public SolutionFileInfo execute(FileUpload file) {
        File fileSaved = storeFilePort.execute("solution", file);
        return solutionFileMapper.toInfo(fileSaved, signFileUrlPort.execute(fileSaved.getId()));
    }
}
