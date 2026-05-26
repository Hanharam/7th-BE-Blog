package com.leets.blog.post.application.port.in.commad.dto;

import static java.util.Objects.requireNonNull;

public record DeletePostCommand(
        Long postId,
        Long requesterId  // 삭제 요청자
) {
    public DeletePostCommand {
        requireNonNull(postId, "게시글 ID는 null일 수 없습니다.");
        requireNonNull(requesterId, "요청자 ID는 null일 수 없습니다.");
    }
}
