package com.leets.blog.post.adapter.in.web.dto.request;

import com.leets.blog.post.application.port.in.commad.dto.CreatePostCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "게시글 작성 요청")
public record CreatePostRequest(
        @Schema(description = "제목", example = "안녕하세요.")
        @NotBlank(message = "제목은 필수입니다.")
        String title,

        @Schema(description = "내용", example = "오늘도 게시글 하나 작성합니다.")
        @NotBlank(message = "내용을 필수입니다.")
        String content,

        @Schema(description = "이미지")
        String imageUrl
) {
    // Web DTO -> Application Command 변환
    public CreatePostCommand toCommand(Long memberId) {
        return new CreatePostCommand(title, content, imageUrl, memberId);
    }
}
