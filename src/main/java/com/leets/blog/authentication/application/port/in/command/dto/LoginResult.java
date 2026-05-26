package com.leets.blog.authentication.application.port.in.command.dto;

import lombok.Builder;

@Builder
public record LoginResult(
        Long memberId,
        String email,
        String nickname,
        String accessToken,
        String refreshToken
) {
}
