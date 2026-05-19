package com.leets.blog.authentication.application.port.in.command.dto;

import java.util.Objects;

public record KakaoLoginCommand(
        String authorizationCode
) {
    public KakaoLoginCommand {
        Objects.requireNonNull(authorizationCode, "카카오 인가 코드는 필수입니다.");
    }
}
