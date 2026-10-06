package com.marcos.estik.user.adapter.in.web.response;

import java.util.List;

public record PageResponse<T>(
    List<T> content,
    int page,
    int size,
    long totalItems,
    int totalPages
) {
}
