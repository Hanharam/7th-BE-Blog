package com.leets.blog.report.adapter.out.persistence;

import com.leets.blog.report.adapter.out.persistence.entity.ReportJpaEntity;
import com.leets.blog.report.domain.enums.ReportTargetType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<ReportJpaEntity, Long> {
    boolean existsByReporterIdAndTargetTypeAndTargetId(Long reporterId, ReportTargetType targetType, Long targetId);
}
