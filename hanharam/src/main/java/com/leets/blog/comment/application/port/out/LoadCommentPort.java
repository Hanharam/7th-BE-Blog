package com.leets.blog.comment.application.port.out;

public interface LoadCommentPort {
    // TODO: Comment 도메인 생성 후 exists 기반 검증 대신 도메인 조회 책임으로 변경
    boolean existsById(Long commentId);
}
