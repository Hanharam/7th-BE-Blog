package com.leets.blog.global.exception.constant;

/**
 * 의도적으로 발생한 Business Exception의 경우, 도메인을 구분하기 위해 사용됨
 */
public enum Domain {
    COMMON,
    REPEAT,
    AUTHORIZATION,
    AUTHENTICATION
}
