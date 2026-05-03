package com.leets.blog.report.domain.exception;

import com.leets.blog.global.exception.BusinessException;
import com.leets.blog.global.exception.constant.Domain;

public class ReportDomainException extends BusinessException {
    public ReportDomainException(ReportErrorCode errorCode) {
        super(Domain.REPORT, errorCode);
    }

    public ReportDomainException(ReportErrorCode errorCode, String message) { super(Domain.REPORT, errorCode, message); }
}
