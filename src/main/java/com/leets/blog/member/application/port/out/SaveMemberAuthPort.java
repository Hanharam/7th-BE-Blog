package com.leets.blog.member.application.port.out;

import com.leets.blog.member.domain.Member;

public interface SaveMemberAuthPort {
    Member save(Member member);
}
