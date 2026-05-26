package com.leets.blog.report.application.port.in.command.dto;

import java.util.Objects;

public record ReportCommentCommand(
        Long commentId,
        Long reporterId,
        String reason
) {
    public ReportCommentCommand{
        Objects.requireNonNull(commentId, "댓글 ID는 필수입니다.");
        Objects.requireNonNull(reporterId, "신고자 ID는 필수입니다.");
        Objects.requireNonNull(reason, "신고 사유는 필수입니다.");
    }
}
