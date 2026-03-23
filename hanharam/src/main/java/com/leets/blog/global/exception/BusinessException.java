package com.leets.blog.global.exception;

import com.leets.blog.global.exception.constant.Domain;
import com.leets.blog.global.response.code.BaseCode;
import lombok.Getter;

/**
 * 비즈니스 로직에서 의도적으로 발생시킨 오류를 정의하는 클래스
 */
@Getter
public class BusinessException extends RuntimeException {
    private final Domain domain;
    private final BaseCode baseCode;
    private final String message;

    public BusinessException(Domain domain, BaseCode baseCode, String message) {
        super(message != null ? message : baseCode.getMessage());
        this.domain = domain;
        this.baseCode = baseCode;
        this.message = message;
    }

    public BusinessException(Domain domain, BaseCode baseCode) {
        this.domain = domain;
        this.baseCode = baseCode;
        this.message = null;
    }
}
