package com.leets.blog.authentication.adapter.in.web.dto.request;

import com.leets.blog.authentication.application.port.in.command.dto.KakaoLoginCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "카카오 로그인 요청")
public record KakaoLoginRequest(
        @Schema(description = "카카오 authorization code", example = "SplxlOBeZQQYbYS6WxSbIA")
        @NotBlank(message = "카카오 인가 코드는 필수입니다.")
        String authorizationCode
) {
    public KakaoLoginCommand toCommand() {
        return new KakaoLoginCommand(authorizationCode);
    }
}
