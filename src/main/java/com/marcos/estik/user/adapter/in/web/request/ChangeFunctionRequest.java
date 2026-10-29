package com.marcos.estik.user.adapter.in.web.request;

import com.marcos.estik.shared.domain.user.FunctionEnum;

public record ChangeFunctionRequest(
    FunctionEnum function
) {
    
}
