package com.leets.blog.post.adapter.in.web;

import com.leets.blog.common.pagination.PageResponse;
import com.leets.blog.post.application.port.in.query.GetPostDetailUseCase;
import com.leets.blog.post.application.port.in.query.GetPostListUseCase;
import com.leets.blog.post.application.port.in.query.dto.PostInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import com.leets.blog.common.pagination.PageRequest;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
@Tag(name = "Post | 게시글 Query", description = "")
public class PostQueryController {

    private final GetPostDetailUseCase getPostDetailUseCase;
    private final GetPostListUseCase getPostListUseCase;

    @GetMapping
    @Operation(summary = "게시글 목록 조회", description = "게시글 목록을 조회합니다.")
    public PageResponse<PostInfo> getPosts(
            @Valid @ParameterObject PageRequest pageRequest
    ) {
        return getPostListUseCase.getPostList(pageRequest);
    }

    @GetMapping("/{postId}")
    @Operation(summary = "게시글 상세 조회", description = "게시글 상세 정보를 조회합니다.")
    public PostInfo getPost(
            @PathVariable Long postId
    ) {
        return getPostDetailUseCase.getPostDetail(postId);
    }
}
