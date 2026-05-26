package com.leets.blog.authentication.adapter.in.web.dto.request;

import com.leets.blog.authentication.application.port.in.command.dto.RefreshTokenCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "리프레시 토큰 재발급 요청")
public record RefreshTokenRequest(
        @Schema(description = "리프레시 토큰", example = "eyJhbGciOiJIUzI1NiJ9...")
        @NotBlank(message = "리프레시 토큰은 필수입니다.")
        String refreshToken
) {
    public RefreshTokenCommand toCommand() {
        return new RefreshTokenCommand(refreshToken);
    }
}
