package com.leets.blog.repeat.exception;

import com.leets.blog.global.exception.BusinessException;
import com.leets.blog.global.exception.constant.Domain;

public class RepeatDomainException extends BusinessException {

    public RepeatDomainException(RepeatErrorCode errorCode) {super(Domain.REPEAT, errorCode);}

    public RepeatDomainException(RepeatErrorCode errorCode, String message) {
        super(Domain.REPEAT, errorCode, message);
    }
}
