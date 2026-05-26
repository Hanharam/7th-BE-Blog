package com.leets.blog.post.application.port.in.commad.dto;

import java.util.Objects;

public record CreatePostCommand(
        String title,
        String content,
        String imageUrl,
        Long memberId
) {
    public CreatePostCommand {
        Objects.requireNonNull(title, "제목은 필수입니다.");
        Objects.requireNonNull(content, "내용은 필수입니다.");
        Objects.requireNonNull(memberId, "작성자 아이디는 필수입니다.");
    }
}
