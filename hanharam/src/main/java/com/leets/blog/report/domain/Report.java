package com.leets.blog.report.domain;

import com.leets.blog.report.domain.enums.ReportStatus;
import com.leets.blog.report.domain.enums.ReportTargetType;
import com.leets.blog.report.domain.exception.ReportDomainException;
import com.leets.blog.report.domain.exception.ReportErrorCode;
import lombok.*;

import java.time.LocalDateTime;


@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
public class Report{
    @Getter
    private final ReportId reportId;

    @Getter
    private final Long reporterId;  // 신고자 ID

    @Getter
    private final ReportTargetType targetType;  // COMMENT, POST

    @Getter
    private final Long targetId;

    @Getter
    private ReportStatus reportStatus;  // PENDING, APPROVED, REJECTED

    @Getter
    private final String reason;    // 신고 사유

    @Getter
    private final LocalDateTime createdAt;

    // 신고 생성
    public static Report create(Long reporterId, ReportTargetType targetType, Long targetId, String reason) {
        validateReporterId(reporterId);
        validateTargetType(targetType);
        validateTargetId(targetId);
        validateReason(reason);

        // 생성 시점의 상태는 대기로 초기화
        ReportStatus initialStatus = ReportStatus.PENDING;
        LocalDateTime now = LocalDateTime.now();

        return Report.builder()
                .reporterId(reporterId)
                .targetType(targetType)
                .targetId(targetId)
                .reportStatus(initialStatus)
                .reason(reason)
                .createdAt(now)
                .build();
    }

    // JPA Entity -> Domain
    public static Report reconstruct(
            ReportId reportId,
            Long reporterId,
            ReportTargetType targetType,
            Long targetId,
            ReportStatus reportStatus,
            String reason) {

        validateReporterId(reporterId);
        validateTargetType(targetType);
        validateTargetId(targetId);
        validateReason(reason);

        return Report.builder()
                .reportId(reportId)
                .reporterId(reporterId)
                .targetType(targetType)
                .targetId(targetId)
                .reportStatus(reportStatus)
                .reason(reason)
                .build();
    }

    public void markAsReviewing() {
        if (this.reportStatus != ReportStatus.PENDING) {
            throw new ReportDomainException(ReportErrorCode.INVALID_STATUS_TRANSITION);
        }

        this.reportStatus = ReportStatus.REVIEWING;
    }

    // 신고자 아이디 검증
    private static void validateReporterId(Long reporterId) {
        if (reporterId == null || reporterId <= 0) {
            throw new ReportDomainException(ReportErrorCode.INVALID_REPORTER_ID);
        }
    }
    // 타겟 유형 검증
    private static void validateTargetType(ReportTargetType targetType) {
        if (targetType == null) {
            throw new ReportDomainException(ReportErrorCode.INVALID_TARGET_TYPE);
        }
    }
    // 타겟 아이디 검증
    private static void validateTargetId(Long targetId) {
        if (targetId == null || targetId <= 0) {
            throw new ReportDomainException(ReportErrorCode.INVALID_TARGET_ID);
        }
    }
    // 신고 사유 검증
    private static void validateReason(String reason) {
        if (reason == null || reason.isBlank()) {
            throw new ReportDomainException(ReportErrorCode.INVALID_REPORT_REASON);
        }
        // 신고 사유 250자로 제한
        if (reason.length() > 250) {
            throw new ReportDomainException(ReportErrorCode.INVALID_REASON_SIZE);
        }
    }
    public record ReportId(Long id) {
        public ReportId {
            if (id <= 0) {
                throw new ReportDomainException(ReportErrorCode.INVALID_ID);
            }
        }
    }
}
