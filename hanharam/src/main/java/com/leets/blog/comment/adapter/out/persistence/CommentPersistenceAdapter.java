package com.leets.blog.comment.adapter.out.persistence;

import com.leets.blog.comment.application.port.out.LoadCommentPort;
import com.leets.blog.comment.domain.CommentJpaEntity;
import com.leets.blog.post.adapter.out.persistence.entity.PostJpaEntity;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CommentPersistenceAdapter implements LoadCommentPort {

    private final CommentRepository commentRepository;

    @Override
    public Optional<Comment> findById(Long commentId) {
        return commentRepository.findById(commentId)
                .map(CommentJpaEntity::toDomain);
    }
}
