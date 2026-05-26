package com.leets.blog.post.adapter.in.web.dto.request;

import com.leets.blog.post.application.port.in.commad.dto.UpdatePostCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "게시글 수정 요청")
public record UpdatePostRequest (
        @Schema(description = "제목", example = "수정한 제목입니다.")
        @NotBlank(message = "제목은 필수입니다.")
        String title,

        @Schema(description = "내용", example = "오늘도 게시글 하나 수정합니다.")
        @NotBlank(message = "내용을 필수입니다.")
        String content,

        @Schema(description = "이미지")
        String imageUrl
){
    public UpdatePostCommand toCommand(Long postId, Long requesterId) {
        return new UpdatePostCommand(
                postId,
                this.title,
                this.content,
                this.imageUrl,
                requesterId
        );
    }
}
