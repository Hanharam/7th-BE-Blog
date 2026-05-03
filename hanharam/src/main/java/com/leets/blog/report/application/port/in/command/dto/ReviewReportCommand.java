package com.leets.blog.report.application.port.in.command.dto;

import java.util.Objects;

public record ReviewReportCommand(
        Long reportId
) {
    public ReviewReportCommand {
        Objects.requireNonNull(reportId, "신고 ID는 필수입니다.");
    }
}
