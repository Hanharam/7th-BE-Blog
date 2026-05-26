package com.leets.blog.post.application.port.in.commad;

import com.leets.blog.post.application.port.in.commad.dto.CreatePostCommand;
import com.leets.blog.post.domain.Post.PostId;

public interface CreatePostUseCase {
    PostId createPost(CreatePostCommand command);
}
