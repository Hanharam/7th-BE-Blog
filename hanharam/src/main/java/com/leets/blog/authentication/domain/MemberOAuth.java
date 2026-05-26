package com.leets.blog.authentication.domain;

import com.leets.blog.authentication.domain.enums.OAuthProvider;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberOAuth {

    private final Long id;
    private final Long memberId;
    private final OAuthProvider provider;
    private final String providerId;

    public static MemberOAuth create(Long memberId, OAuthProvider provider, String providerId) {
        return MemberOAuth.builder()
                .memberId(memberId)
                .provider(provider)
                .providerId(providerId)
                .build();
    }

    public static MemberOAuth reconstruct(Long id, Long memberId, OAuthProvider provider, String providerId) {
        return MemberOAuth.builder()
                .id(id)
                .memberId(memberId)
                .provider(provider)
                .providerId(providerId)
                .build();
    }
}
