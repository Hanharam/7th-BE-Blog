package com.leets.blog.post.application.port.out;

import com.leets.blog.common.pagination.PageRequest;
import com.leets.blog.common.pagination.PageResponse;
import com.leets.blog.post.domain.Post;
import com.leets.blog.post.domain.Post.PostId;

import java.util.Optional;

public interface LoadPostPort {

    Post findPost(PostId postId);

    Optional<Post> findById(Long postId);

    PageResponse<Post> findAll(PageRequest pageRequest);
}
