package com.leets.blog.post.domain.exception;

import com.leets.blog.global.exception.BusinessException;
import com.leets.blog.global.exception.constant.Domain;

public class PostDomainException extends BusinessException {
    public PostDomainException(PostErrorCode errorCode) {
        super(Domain.POST, errorCode);
    }

    public PostDomainException(PostErrorCode errorCode, String message) {
        super(Domain.POST, errorCode, message);
    }
}
