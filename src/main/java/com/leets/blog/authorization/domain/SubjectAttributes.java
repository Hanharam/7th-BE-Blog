package com.leets.blog.authorization.domain;

import com.leets.blog.common.enums.Role;
import java.util.Objects;

/**
 * 권한 판단에 필요한 현재 사용자 속성
 */
public record SubjectAttributes(
        Long memberId,
        Role role
) {
    public SubjectAttributes {
        Objects.requireNonNull(memberId, "회원 ID는 필수입니다.");
        Objects.requireNonNull(role, "회원 역할은 필수입니다.");
    }
}
