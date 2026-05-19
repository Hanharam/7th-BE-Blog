package com.leets.blog.authentication.application.port.out;

import com.leets.blog.authentication.domain.MemberRefreshToken;

public interface SaveMemberRefreshTokenPort {
    MemberRefreshToken save(MemberRefreshToken memberRefreshToken);
}
