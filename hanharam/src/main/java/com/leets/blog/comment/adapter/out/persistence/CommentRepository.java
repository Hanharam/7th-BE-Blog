package com.leets.blog.comment.adapter.out.persistence;

import com.leets.blog.comment.domain.CommentJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<CommentJpaEntity, Long> {
}
