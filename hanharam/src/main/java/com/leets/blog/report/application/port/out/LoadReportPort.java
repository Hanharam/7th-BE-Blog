package com.leets.blog.report.application.port.out;

import com.leets.blog.report.domain.Report;
import com.leets.blog.report.domain.enums.ReportTargetType;

public interface LoadReportPort {
    boolean existsByReporterIdAndTargetTypeAndTargetId(Long reporterId, ReportTargetType targetType, Long targetId);

    Report findReport(Report.ReportId reportId);
}
