package com.leets.blog.authentication.adapter.out.persistence.entity;

import com.leets.blog.authentication.domain.MemberRefreshToken;
import com.leets.blog.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(
        name = "member_refresh_token",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_member_refresh_token_token_id", columnNames = "token_id")
        }
)
public class MemberRefreshTokenJpaEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "member_id", nullable = false)
    private Long memberId;

    @Column(name = "token_id", nullable = false, length = 100)
    private String tokenId;

    @Column(name = "expires_at", nullable = false)
    private LocalDateTime expiresAt;

    @Builder
    private MemberRefreshTokenJpaEntity(Long memberId, String tokenId, LocalDateTime expiresAt) {
        this.memberId = memberId;
        this.tokenId = tokenId;
        this.expiresAt = expiresAt;
    }

    public static MemberRefreshTokenJpaEntity from(MemberRefreshToken memberRefreshToken) {
        return MemberRefreshTokenJpaEntity.builder()
                .memberId(memberRefreshToken.getMemberId())
                .tokenId(memberRefreshToken.getTokenId())
                .expiresAt(memberRefreshToken.getExpiresAt())
                .build();
    }

    public MemberRefreshToken toDomain() {
        return MemberRefreshToken.reconstruct(
                id,
                memberId,
                tokenId,
                expiresAt
        );
    }
}
