package com.leets.blog.domain.dto.res;

import lombok.Builder;

@Builder
public record RepeatResDTO(
        String string_one,
        String string_two
) {}

