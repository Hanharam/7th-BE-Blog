package com.leets.blog.authentication.application.port.out;

import com.leets.blog.authentication.domain.MemberOAuth;

public interface SaveMemberOAuthPort {
    MemberOAuth save(MemberOAuth memberOAuth);
}
