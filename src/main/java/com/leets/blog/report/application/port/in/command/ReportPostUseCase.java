package com.leets.blog.report.application.port.in.command;

import com.leets.blog.report.application.port.in.command.dto.ReportPostCommand;

public interface ReportPostUseCase {
    void report(ReportPostCommand command);
}
