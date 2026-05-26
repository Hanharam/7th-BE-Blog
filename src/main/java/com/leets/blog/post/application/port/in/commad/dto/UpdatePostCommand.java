package com.leets.blog.post.application.port.in.commad.dto;

import java.util.Objects;

public record UpdatePostCommand(
        Long postId,
        String title,
        String content,
        String imageUrl,
        Long requesterId
) {
    public UpdatePostCommand {
        Objects.requireNonNull(postId, "게시글 아이디는 필수입니다.");
        Objects.requireNonNull(requesterId, "수정하려는 회원의 아이디는 필수입니다.");
        Objects.requireNonNull(title, "제목은 필수입니다.");
        Objects.requireNonNull(content, "내용은 필수입니다.");
    }
}

