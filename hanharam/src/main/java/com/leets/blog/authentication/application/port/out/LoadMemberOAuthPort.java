package com.leets.blog.authentication.application.port.out;

import com.leets.blog.authentication.domain.MemberOAuth;
import com.leets.blog.authentication.domain.enums.OAuthProvider;
import java.util.Optional;

public interface LoadMemberOAuthPort {
    Optional<MemberOAuth> findByProviderAndProviderId(OAuthProvider provider, String providerId);
}
