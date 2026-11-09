package com.marcos.estik.access.application.usecases;

import org.springframework.stereotype.Service;

import com.marcos.estik.access.application.ports.in.SearchAccessPort;
import com.marcos.estik.access.application.ports.out.RoleRepositoryPort;
import com.marcos.estik.access.domain.Role;
import com.marcos.estik.shared.application.pagination.PageQuery;
import com.marcos.estik.shared.application.pagination.PageResult;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class SearchAccessUseCase implements SearchAccessPort {

    private final RoleRepositoryPort roleRepositoryPort;

    @Override
    public PageResult<Role> execute(PageQuery page) {
        return roleRepositoryPort.searchAccess(page);
    }    
}