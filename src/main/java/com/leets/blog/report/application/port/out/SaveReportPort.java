package com.leets.blog.report.application.port.out;

import com.leets.blog.report.domain.Report;

public interface SaveReportPort {
    Report save(Report report);
}
