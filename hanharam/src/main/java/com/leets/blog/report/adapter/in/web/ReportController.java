package com.leets.blog.report.adapter.in.web;

import com.leets.blog.global.security.MemberPrincipal;
import com.leets.blog.global.security.annotation.CurrentMember;
import com.leets.blog.report.adapter.in.web.dto.request.CreateReportRequest;
import com.leets.blog.report.application.port.in.command.ReportCommentUseCase;
import com.leets.blog.report.application.port.in.command.ReportPostUseCase;
import com.leets.blog.report.application.port.in.command.ReviewReportUseCase;
import com.leets.blog.report.application.port.in.command.dto.ReviewReportCommand;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/reports")
@RequiredArgsConstructor
@Tag(name = "Report | 신고 command", description = "신고 관련 API")
@SecurityRequirement(name = "bearerAuth")
public class ReportController {

    private final ReportCommentUseCase reportCommentUseCase;
    private final ReportPostUseCase reportPostUseCase;
    private final ReviewReportUseCase reviewReportUseCase;

    @PostMapping("/posts/{postId}")
    @Operation(summary = "게시글 신고", description = "특정 게시글을 신고합니다.")
    public void reportPost(
            @PathVariable Long postId,
            @Valid @RequestBody CreateReportRequest request,
            @Parameter(hidden = true)
            @CurrentMember MemberPrincipal memberPrincipal
    ) {
        reportPostUseCase.report(request.toPostCommand(postId, memberPrincipal.getMemberId()));
    }

    @PostMapping("/comments/{commentId}")
    @Operation(summary = "댓글 신고", description = "특정 댓글을 신고합니다.")
    public void reportComment(
            @PathVariable Long commentId,
            @Valid @RequestBody CreateReportRequest request,
            @Parameter(hidden = true)
            @CurrentMember MemberPrincipal memberPrincipal
    ) {
        reportCommentUseCase.report(request.toCommentCommand(commentId, memberPrincipal.getMemberId()));
    }

    @PatchMapping("/{reportId}/reviewing")
    @Operation(summary = "신고 검토중 처리", description = "특정 신고를 검토중 상태로 변경합니다.")
    public void reviewReport(@PathVariable Long reportId) {
        reviewReportUseCase.review(new ReviewReportCommand(reportId));
    }
}
