package com.leets.blog.comment.application.port.out;

import org.hibernate.annotations.Comment;

import java.util.Optional;

public interface LoadCommentPort {
    Optional<Comment> findById(Long commentId);
}
