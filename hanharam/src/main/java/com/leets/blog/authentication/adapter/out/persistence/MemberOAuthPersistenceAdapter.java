package com.leets.blog.authentication.adapter.out.persistence;

import com.leets.blog.authentication.adapter.out.persistence.entity.MemberOAuthJpaEntity;
import com.leets.blog.authentication.application.port.out.LoadMemberOAuthPort;
import com.leets.blog.authentication.application.port.out.SaveMemberOAuthPort;
import com.leets.blog.authentication.domain.MemberOAuth;
import com.leets.blog.authentication.domain.enums.OAuthProvider;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberOAuthPersistenceAdapter implements LoadMemberOAuthPort, SaveMemberOAuthPort {

    private final MemberOAuthRepository memberOAuthRepository;

    @Override
    public Optional<MemberOAuth> findByProviderAndProviderId(OAuthProvider provider, String providerId) {
        return memberOAuthRepository.findByProviderAndProviderId(provider, providerId)
                .map(MemberOAuthJpaEntity::toDomain);
    }

    @Override
    public MemberOAuth save(MemberOAuth memberOAuth) {
        MemberOAuthJpaEntity saved = memberOAuthRepository.save(MemberOAuthJpaEntity.from(memberOAuth));
        return saved.toDomain();
    }
}
