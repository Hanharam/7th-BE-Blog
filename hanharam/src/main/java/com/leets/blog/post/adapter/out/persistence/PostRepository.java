package com.leets.blog.post.adapter.out.persistence;

import com.leets.blog.post.adapter.out.persistence.entity.PostJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostJpaEntity, Long> {
}
