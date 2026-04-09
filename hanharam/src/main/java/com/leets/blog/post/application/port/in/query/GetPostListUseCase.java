package com.leets.blog.post.application.port.in.query;


import com.leets.blog.common.pagination.PageResponse;
import com.leets.blog.post.application.port.in.query.dto.PostInfo;
import com.leets.blog.common.pagination.PageRequest;

public interface GetPostListUseCase {
    PageResponse<PostInfo> getPostList(PageRequest pageRequest);
}
