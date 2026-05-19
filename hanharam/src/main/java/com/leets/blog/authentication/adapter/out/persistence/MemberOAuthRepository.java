package com.leets.blog.authentication.adapter.out.persistence;

import com.leets.blog.authentication.adapter.out.persistence.entity.MemberOAuthJpaEntity;
import com.leets.blog.authentication.domain.enums.OAuthProvider;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberOAuthRepository extends JpaRepository<MemberOAuthJpaEntity, Long> {
    Optional<MemberOAuthJpaEntity> findByProviderAndProviderId(OAuthProvider provider, String providerId);
}
