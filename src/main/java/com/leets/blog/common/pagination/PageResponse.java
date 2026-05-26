package com.leets.blog.common.pagination;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public record PageResponse<T>(
        List<T> content,
        int page,
        int size,
        long totalElements,
        int totalPages,
        boolean hasNext,
        boolean hasPrevious
) {
    public <R> PageResponse<R> map(Function<? super T, ? extends R> mapper) {
        List<R> mappedContent = content.stream()
                .map(mapper)
                .collect(Collectors.toList());

        return new PageResponse<>(
                mappedContent,
                page,
                size,
                totalElements,
                totalPages,
                hasNext,
                hasPrevious
        );
    }
}
