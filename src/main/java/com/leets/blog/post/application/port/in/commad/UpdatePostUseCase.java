package com.leets.blog.post.application.port.in.commad;

import com.leets.blog.post.application.port.in.commad.dto.UpdatePostCommand;
import com.leets.blog.post.domain.Post.PostId;

public interface UpdatePostUseCase {
    PostId updatePost(UpdatePostCommand command);
}
