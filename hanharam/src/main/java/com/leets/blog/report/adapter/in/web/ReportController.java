package com.leets.blog.report.adapter.in.web;

import com.leets.blog.report.adapter.in.web.dto.request.CreateReportRequest;
import com.leets.blog.report.application.port.in.command.ReportCommentUseCase;
import com.leets.blog.report.application.port.in.command.ReportPostUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/reports")
@RequiredArgsConstructor
@Tag(name = "Report | 신고 command", description = "신고 관련 API")
public class ReportController {

    private final ReportCommentUseCase reportCommentUseCase;
    private final ReportPostUseCase reportPostUseCase;

    @PostMapping("/posts/{postId}")
    @Operation(summary = "게시글 신고", description = "특정 게시글을 신고합니다.")
    public void reportPost(
            @PathVariable Long postId,
            @Valid @RequestBody CreateReportRequest request,
            Long reporterId
    ) {
        reportPostUseCase.report(request.toPostCommand(postId, reporterId));
    }

    @PostMapping("/comments/{commentId}")
    @Operation(summary = "댓글 신고", description = "특정 댓글을 신고합니다.")
    public void reportComment(
            @PathVariable Long commentId,
            @Valid @RequestBody CreateReportRequest request,
            Long reporterId
    ) {
        reportCommentUseCase.report(request.toCommentCommand(commentId, reporterId));
    }
}
