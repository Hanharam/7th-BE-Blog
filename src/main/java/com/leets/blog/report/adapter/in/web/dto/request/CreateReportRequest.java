package com.leets.blog.report.adapter.in.web.dto.request;

import com.leets.blog.report.application.port.in.command.dto.ReportCommentCommand;
import com.leets.blog.report.application.port.in.command.dto.ReportPostCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "신고 사유 작성 요청")
public record CreateReportRequest(
        @Schema(description = "신고 사유", example = "부적절한 언어 포함")
        @NotBlank(message = "신고 사유는 필수입니다.")
        String reason
) {
    public ReportCommentCommand toCommentCommand(Long commentId, Long reporterId) {
        return new ReportCommentCommand(commentId, reporterId, reason);
    }

    public ReportPostCommand toPostCommand(Long postId, Long reporterId) {
        return new ReportPostCommand(postId, reporterId, reason);
    }
}
