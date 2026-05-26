package com.leets.blog.authentication.application.port.out.dto;

public record KakaoOAuthUserInfo(
        String providerId,
        String email,
        String nickname
) {
}
