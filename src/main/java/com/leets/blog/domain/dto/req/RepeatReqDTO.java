package com.leets.blog.domain.dto.req;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;



@Builder
public record RepeatReqDTO(
        @NotBlank(message = "문장 입력은 필수입니다!!")
        String value
) {}
