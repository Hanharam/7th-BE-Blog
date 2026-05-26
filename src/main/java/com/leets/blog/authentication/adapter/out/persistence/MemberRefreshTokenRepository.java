package com.leets.blog.authentication.adapter.out.persistence;

import com.leets.blog.authentication.adapter.out.persistence.entity.MemberRefreshTokenJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRefreshTokenRepository extends JpaRepository<MemberRefreshTokenJpaEntity, Long> {
    long deleteByTokenIdAndMemberId(String tokenId, Long memberId);
}
