package com.leets.blog.report.domain.exception;

import com.leets.blog.global.response.code.BaseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ReportErrorCode implements BaseCode {

    INVALID_ID(HttpStatus.BAD_REQUEST, "REPORT-001", "ID는 양수입니다."),
    INVALID_REPORTER_ID(HttpStatus.BAD_REQUEST, "REPORT-002", "신고자ID는 필수입니다."),
    INVALID_TARGET_TYPE(HttpStatus.BAD_REQUEST, "REPORT-003", "타겟 타입은 필수입니다."),
    INVALID_TARGET_ID(HttpStatus.BAD_REQUEST, "REPORT-004", "타겟ID는 필수입니다."),
    INVALID_REPORT_REASON(HttpStatus.BAD_REQUEST, "REPORT-005", "신고 사유는 필수입니다."),
    INVALID_REASON_SIZE(HttpStatus.BAD_REQUEST, "REPORT-006", "신고 사유가 250자 초과입니다."),
    INVALID_REPORT_STATUS(HttpStatus.BAD_REQUEST, "REPORT-007", "신고 상태는 필수입니다."),

    ALREADY_PROCESSED(HttpStatus.BAD_REQUEST, "REPORT-008", "이미 처리된 신고입니다."),
    POST_NOT_FOUND(HttpStatus.BAD_REQUEST, "REPORT-009", "신고한 게시글을 찾을 수 없습니다."),
    REPORT_ALREADY_EXISTS(HttpStatus.CONFLICT, "REPORT-010", "이미 신고한 게시글/댓글입니다."),
    COMMENT_NOT_FOUND(HttpStatus.BAD_REQUEST, "REPORT-011", "신고한 댓글을 찾을 수 없습니다."),
    REPORT_NOT_FOUND(HttpStatus.BAD_REQUEST, "REPORT-012", "신고를 찾을 수 없습니다."),
    INVALID_STATUS_TRANSITION(HttpStatus.BAD_REQUEST, "REPORT-013", "해당 상태로 변경할 수 없습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
