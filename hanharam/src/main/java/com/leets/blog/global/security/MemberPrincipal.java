package com.leets.blog.global.security;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberPrincipal {

    private final Long memberId;

    @Builder
    public MemberPrincipal(Long memberId) {
        this.memberId = memberId;
    }
}
