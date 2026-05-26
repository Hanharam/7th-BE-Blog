package com.leets.blog.authentication.adapter.in.web.dto.response;

import com.leets.blog.authentication.application.port.in.command.dto.LoginResult;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "인증 응답")
public record AuthResponse(
        Long memberId,
        String email,
        String nickname,
        String accessToken,
        String refreshToken
) {
    public static AuthResponse from(LoginResult result) {
        return new AuthResponse(
                result.memberId(),
                result.email(),
                result.nickname(),
                result.accessToken(),
                result.refreshToken()
        );
    }
}
