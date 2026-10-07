package com.marcos.estik.shared.application.pagination;

public record PageQuery(
    int page, int size
) {
    public PageQuery {
        if (page < 0) throw new IllegalArgumentException("Page must be >= 0");
        if (size <= 0 || size > 100) throw new IllegalArgumentException("Size must be beetwen 1 and 100");
    }
}
