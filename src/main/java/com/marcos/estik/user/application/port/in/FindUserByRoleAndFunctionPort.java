package com.marcos.estik.user.application.port.in;

import java.util.List;

import com.marcos.estik.shared.domain.user.FunctionEnum;
import com.marcos.estik.shared.domain.user.RoleEnum;
import com.marcos.estik.user.domain.User;

public interface FindUserByRoleAndFunctionPort {

    List<User> execute(RoleEnum role, FunctionEnum function);
    
}
