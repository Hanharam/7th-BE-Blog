package com.leets.blog.report.application.port.in.command;

import com.leets.blog.report.application.port.in.command.dto.ReportCommentCommand;

public interface ReportCommentUseCase {
    void report(ReportCommentCommand command);
}
