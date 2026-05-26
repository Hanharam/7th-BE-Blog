package com.leets.blog.authentication.adapter.out.persistence;

import com.leets.blog.authentication.adapter.out.persistence.entity.MemberRefreshTokenJpaEntity;
import com.leets.blog.authentication.application.port.out.ConsumeMemberRefreshTokenPort;
import com.leets.blog.authentication.application.port.out.SaveMemberRefreshTokenPort;
import com.leets.blog.authentication.domain.MemberRefreshToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberRefreshTokenPersistenceAdapter implements SaveMemberRefreshTokenPort, ConsumeMemberRefreshTokenPort {

    private final MemberRefreshTokenRepository memberRefreshTokenRepository;

    @Override
    public MemberRefreshToken save(MemberRefreshToken memberRefreshToken) {
        MemberRefreshTokenJpaEntity saved = memberRefreshTokenRepository.save(
                MemberRefreshTokenJpaEntity.from(memberRefreshToken)
        );
        return saved.toDomain();
    }

    @Override
    public boolean consume(String tokenId, Long memberId) {
        return memberRefreshTokenRepository.deleteByTokenIdAndMemberId(tokenId, memberId) > 0;
    }
}
