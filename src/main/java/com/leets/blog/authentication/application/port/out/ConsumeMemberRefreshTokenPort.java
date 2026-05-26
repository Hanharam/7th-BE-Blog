package com.leets.blog.authentication.application.port.out;

public interface ConsumeMemberRefreshTokenPort {
    boolean consume(String tokenId, Long memberId);
}
