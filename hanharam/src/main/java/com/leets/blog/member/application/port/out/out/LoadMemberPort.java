package com.leets.blog.member.application.port.out.out;

import java.util.Map;
import java.util.Set;

public interface LoadMemberPort {
    // 단건 조회
    String findNicknameById(Long memberId);

    // 일괄 조회 (N+1 방지용)
    Map<Long, String> findNicknamesByIds(Set<Long> memberIds);

    boolean existsById(Long memberId);
}
