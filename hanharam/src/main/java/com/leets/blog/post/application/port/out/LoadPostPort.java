package com.leets.blog.post.application.port.out;

import com.leets.blog.post.domain.Post;
import com.leets.blog.post.domain.Post.PostId;

public interface LoadPostPort {

    Post loadPost(PostId postId);
}
