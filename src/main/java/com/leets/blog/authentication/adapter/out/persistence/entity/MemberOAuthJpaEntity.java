package com.leets.blog.authentication.adapter.out.persistence.entity;

import com.leets.blog.authentication.domain.MemberOAuth;
import com.leets.blog.authentication.domain.enums.OAuthProvider;
import com.leets.blog.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(
        name = "member_oauth",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_provider_provider_id",
                        columnNames = {"provider", "provider_id"}
                )
        }
)
public class MemberOAuthJpaEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "member_id", nullable = false)
    private Long memberId;

    @Enumerated(EnumType.STRING)
    @Column(name = "provider", nullable = false)
    private OAuthProvider provider;

    @Column(name = "provider_id", nullable = false)
    private String providerId;

    @Builder
    private MemberOAuthJpaEntity(Long memberId, OAuthProvider provider, String providerId) {
        this.memberId = memberId;
        this.provider = provider;
        this.providerId = providerId;
    }

    public static MemberOAuthJpaEntity from(MemberOAuth memberOAuth) {
        return MemberOAuthJpaEntity.builder()
                .memberId(memberOAuth.getMemberId())
                .provider(memberOAuth.getProvider())
                .providerId(memberOAuth.getProviderId())
                .build();
    }

    public MemberOAuth toDomain() {
        return MemberOAuth.reconstruct(
                this.id,
                this.memberId,
                this.provider,
                this.providerId
        );
    }
}
