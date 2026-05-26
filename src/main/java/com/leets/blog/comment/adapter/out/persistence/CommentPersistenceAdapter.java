package com.leets.blog.comment.adapter.out.persistence;

import com.leets.blog.comment.application.port.out.LoadCommentPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentPersistenceAdapter implements LoadCommentPort {

    private final CommentRepository commentRepository;

    @Override
    public boolean existsById(Long commentId) {
        return commentRepository.existsById(commentId);
    }
}
