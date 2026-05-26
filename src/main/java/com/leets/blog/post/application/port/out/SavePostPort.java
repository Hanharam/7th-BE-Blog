package com.leets.blog.post.application.port.out;

import com.leets.blog.post.domain.Post;

public interface SavePostPort {
    Post save(Post post);

    void deleteById(Long postId);
}
