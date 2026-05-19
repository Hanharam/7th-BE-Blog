package com.leets.blog.member.application.port.out;

import com.leets.blog.member.domain.Member;
import java.util.Optional;

public interface LoadMemberAuthPort {
    Optional<Member> findById(Long memberId);

    Optional<Member> findByEmail(String email);

    boolean existsByEmail(String email);
}
