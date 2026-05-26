package com.leets.blog.report.application.service;

import com.leets.blog.comment.application.port.out.LoadCommentPort;
import com.leets.blog.post.application.port.out.LoadPostPort;
import com.leets.blog.report.application.port.in.command.ReportCommentUseCase;
import com.leets.blog.report.application.port.in.command.ReportPostUseCase;
import com.leets.blog.report.application.port.in.command.ReviewReportUseCase;
import com.leets.blog.report.application.port.in.command.dto.ReportCommentCommand;
import com.leets.blog.report.application.port.in.command.dto.ReportPostCommand;
import com.leets.blog.report.application.port.in.command.dto.ReviewReportCommand;
import com.leets.blog.report.application.port.out.LoadReportPort;
import com.leets.blog.report.application.port.out.SaveReportPort;
import com.leets.blog.report.domain.Report;
import com.leets.blog.report.domain.enums.ReportTargetType;
import com.leets.blog.report.domain.exception.ReportDomainException;
import com.leets.blog.report.domain.exception.ReportErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReportCommandService implements ReportPostUseCase, ReportCommentUseCase, ReviewReportUseCase {

    private final SaveReportPort saveReportPort;
    private final LoadReportPort loadReportPort;
    private final LoadPostPort loadPostPort;
    private final LoadCommentPort loadCommentPort;

    @Override
    public void report(ReportCommentCommand command) {
        // 댓글 존재 확인
        if (!loadCommentPort.existsById(command.commentId())) {
            throw new ReportDomainException(ReportErrorCode.COMMENT_NOT_FOUND);
        }

        // 중복 신고 확인 및 저장
        checkDuplicateAndSaveReport(command.reporterId(), ReportTargetType.COMMENT, command.commentId(), command.reason());
    }

    @Override
    public void report(ReportPostCommand command) {
        // 게시글 존재 확인
        loadPostPort.findById(command.postId())
                .orElseThrow(() -> new ReportDomainException(ReportErrorCode.POST_NOT_FOUND));

        // 중복 신고 확인 및 저장
        checkDuplicateAndSaveReport(command.reporterId(), ReportTargetType.POST, command.postId(), command.reason());
    }

    @Override
    public void review(ReviewReportCommand command) {
        Report report = loadReportPort.findReport(new Report.ReportId(command.reportId()));
        report.markAsReviewing();
        saveReportPort.save(report);
    }

    /**
     * 중복 신고를 확인하고 신고를 생성합니다.
     *
     * @param reporterId 신고자 ID
     * @param targetType 신고 타겟 유형 (Comment, Post)
     * @param targetId   신고 대상 ID
     * @throws ReportDomainException 이미 신고한 경우
     */

    private void checkDuplicateAndSaveReport(Long reporterId, ReportTargetType targetType, Long targetId, String reason) {
        // 중복 신고 확인
        if (loadReportPort.existsByReporterIdAndTargetTypeAndTargetId(reporterId, targetType, targetId)) {
            throw new ReportDomainException(ReportErrorCode.REPORT_ALREADY_EXISTS);
        }

        // 신고 생성 및 저장
        Report report = Report.create(reporterId, targetType, targetId, reason);
        saveReportPort.save(report);
    }
}
