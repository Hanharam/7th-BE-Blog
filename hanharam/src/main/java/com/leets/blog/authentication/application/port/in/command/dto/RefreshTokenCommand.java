package com.leets.blog.authentication.application.port.in.command.dto;

import java.util.Objects;

public record RefreshTokenCommand(
        String refreshToken
) {
    public RefreshTokenCommand {
        Objects.requireNonNull(refreshToken, "리프레시 토큰은 필수입니다.");
    }
}
