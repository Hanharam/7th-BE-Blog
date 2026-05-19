package com.leets.blog.authentication.application.port.out;

import com.leets.blog.authentication.application.port.out.dto.KakaoOAuthUserInfo;

public interface VerifyKakaoOAuthPort {
    KakaoOAuthUserInfo verifyAuthorizationCode(String authorizationCode);
}
