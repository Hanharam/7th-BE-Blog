package com.leets.blog.post.application.port.in.query;

import com.leets.blog.post.application.port.in.query.dto.PostInfo;

public interface GetPostDetailUseCase {
    PostInfo getPostDetail(Long postId);
}
