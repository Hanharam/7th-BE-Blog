package com.leets.blog.report.adapter.out.persistence.entity;

import com.leets.blog.common.BaseEntity;
import com.leets.blog.report.domain.Report;
import com.leets.blog.report.domain.enums.ReportStatus;
import com.leets.blog.report.domain.enums.ReportTargetType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "report")
public class ReportJpaEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reporter_id", nullable = false)
    private Long reporterId;

    @Enumerated(EnumType.STRING)
    @Column(name = "target_type", nullable = false)
    private ReportTargetType targetType;

    @Column(name = "target_id", nullable = false)
    private Long targetId;

    @Enumerated(EnumType.STRING)
    @Column(name = "report_status", nullable = false)
    private ReportStatus reportStatus;

    @Column(name = "reason", nullable = false, length = 250)
    private String reason;

    @Builder
    private ReportJpaEntity(Long reporterId, ReportTargetType targetType, Long targetId, ReportStatus reportStatus, String reason) {
        this.reporterId = reporterId;
        this.targetType = targetType;
        this.targetId = targetId;
        this.reportStatus = reportStatus;
        this.reason = reason;
    }

    // Domain -> JPA Entity
    public static ReportJpaEntity from(Report report) {
        return ReportJpaEntity.builder()
                .reporterId(report.getReporterId())
                .targetType(report.getTargetType())
                .targetId(report.getTargetId())
                .reportStatus(report.getReportStatus())
                .reason(report.getReason())
                .build();
    }

    public void update(Report report) {
        this.reportStatus = report.getReportStatus();
    }

    // JPA Entity -> Domain
    public Report toDomain() {
        return Report.reconstruct(
                new Report.ReportId(this.id),
                this.reporterId,
                this.targetType,
                this.targetId,
                this.reportStatus,
                this.reason
        );
    }

}
