package com.leets.blog.authentication.domain;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberRefreshToken {

    private final Long id;
    private final Long memberId;
    private final String tokenId;
    private final LocalDateTime expiresAt;

    public static MemberRefreshToken create(Long memberId, String tokenId, LocalDateTime expiresAt) {
        return MemberRefreshToken.builder()
                .memberId(memberId)
                .tokenId(tokenId)
                .expiresAt(expiresAt)
                .build();
    }

    public static MemberRefreshToken reconstruct(
            Long id,
            Long memberId,
            String tokenId,
            LocalDateTime expiresAt
    ) {
        return MemberRefreshToken.builder()
                .id(id)
                .memberId(memberId)
                .tokenId(tokenId)
                .expiresAt(expiresAt)
                .build();
    }
}
