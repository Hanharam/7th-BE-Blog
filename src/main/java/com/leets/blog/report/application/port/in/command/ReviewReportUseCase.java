package com.leets.blog.report.application.port.in.command;

import com.leets.blog.report.application.port.in.command.dto.ReviewReportCommand;

public interface ReviewReportUseCase {
    void review(ReviewReportCommand command);
}
