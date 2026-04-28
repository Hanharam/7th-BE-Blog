package com.leets.blog.report.adapter.out.persistence;

import com.leets.blog.report.adapter.out.persistence.entity.ReportJpaEntity;
import com.leets.blog.report.application.port.out.LoadReportPort;
import com.leets.blog.report.application.port.out.SaveReportPort;
import com.leets.blog.report.domain.Report;
import com.leets.blog.report.domain.enums.ReportTargetType;
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
    public Report save(Report report) {
        ReportJpaEntity entity = ReportJpaEntity.from(report);

        ReportJpaEntity saved = reportRepository.save(entity);

        return saved.toDomain();
    }
}
