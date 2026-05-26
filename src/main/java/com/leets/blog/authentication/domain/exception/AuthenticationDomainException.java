package com.leets.blog.authentication.domain.exception;

import com.leets.blog.global.exception.BusinessException;
import com.leets.blog.global.exception.constant.Domain;

public class AuthenticationDomainException extends BusinessException {

    public AuthenticationDomainException(AuthenticationErrorCode errorCode) {
        super(Domain.AUTHENTICATION, errorCode);
    }

    public AuthenticationDomainException(AuthenticationErrorCode errorCode, String message) {
        super(Domain.AUTHENTICATION, errorCode, message);
    }
}
