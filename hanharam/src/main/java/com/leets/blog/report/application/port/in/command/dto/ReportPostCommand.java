package com.leets.blog.report.application.port.in.command.dto;

import java.util.Objects;

public record ReportPostCommand(
        Long postId,
        Long reporterId,
        String reason
) {
    public ReportPostCommand{
        Objects.requireNonNull(postId, "게시글 ID는 필수입니다.");
        Objects.requireNonNull(reporterId, "신고자 ID는 필수입니다.");
        Objects.requireNonNull(reason, "신고 사유는 필수입니다.");
    }
}
