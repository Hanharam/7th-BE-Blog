package com.leets.blog.common.pagination;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public record PageRequest(
        @Min(1) Integer page,
        @Min(1) @Max(100) Integer size
) {
    public PageRequest {
        if (page == null) page = 1;
        if (size == null) size = 10;
    }

    public long offset() {
        return (long) (page - 1) * size;
    }
}
