package com.leets.blog.repeat.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;



@Builder
public record RepeatRequest(
        @Schema(description = "null, 빈 문자열, 공백일 때 에러 반환")
        @NotBlank(message = "문장 입력은 필수입니다.")
        String value
) {}
