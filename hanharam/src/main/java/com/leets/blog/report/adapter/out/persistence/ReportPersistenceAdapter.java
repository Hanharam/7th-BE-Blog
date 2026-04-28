package com.leets.blog.report.adapter.out.persistence;

import com.leets.blog.report.adapter.out.persistence.entity.ReportJpaEntity;
import com.leets.blog.report.application.port.out.LoadReportPort;
import com.leets.blog.report.application.port.out.SaveReportPort;
import com.leets.blog.report.domain.Report;
import com.leets.blog.report.domain.enums.ReportTargetType;
import com.leets.blog.report.domain.exception.ReportDomainException;
import com.leets.blog.report.domain.exception.ReportErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReportPersistenceAdapter implements LoadReportPort, SaveReportPort {

    private final ReportRepository reportRepository;

    @Override
    public boolean existsByReporterIdAndTargetTypeAndTargetId(Long reporterId, ReportTargetType targetType, Long targetId) {

        return reportRepository.existsByReporterIdAndTargetTypeAndTargetId(reporterId, targetType, targetId);
    }

    @Override
    public Report findReport(Report.ReportId reportId) {
        return reportRepository.findById(reportId.id())
                .map(ReportJpaEntity::toDomain)
                .orElseThrow(() -> new ReportDomainException(ReportErrorCode.REPORT_NOT_FOUND));
    }

    @Override
    public Report save(Report report) {
        ReportJpaEntity entity;

        if (report.getReportId() == null) {
            entity = ReportJpaEntity.from(report);
        } else {
            entity = reportRepository.findById(report.getReportId().id())
                    .orElseThrow(() -> new ReportDomainException(ReportErrorCode.REPORT_NOT_FOUND));
            entity.update(report);
        }

        ReportJpaEntity saved = reportRepository.save(entity);

        return saved.toDomain();
    }
}
