package com.leets.blog.post.application.port.in.commad;

import com.leets.blog.post.application.port.in.commad.dto.DeletePostCommand;

public interface DeletePostUseCase {
    void deletePost(DeletePostCommand command);
}
