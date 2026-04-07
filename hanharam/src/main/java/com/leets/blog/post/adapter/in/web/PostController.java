package com.leets.blog.post.adapter.in.web;

import com.leets.blog.post.adapter.in.web.dto.request.CreatePostRequest;
import com.leets.blog.post.adapter.in.web.dto.request.UpdatePostRequest;
import com.leets.blog.post.application.port.in.commad.CreatePostUseCase;
import com.leets.blog.post.application.port.in.commad.DeletePostUseCase;
import com.leets.blog.post.application.port.in.commad.UpdatePostUseCase;
import com.leets.blog.post.application.port.in.commad.dto.DeletePostCommand;
import com.leets.blog.post.domain.Post.PostId;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
@Tag(name = "Post | 게시글 command", description = "게시글 관련 API")
public class PostController {

    private final CreatePostUseCase createPostUseCase;
    private final UpdatePostUseCase updatePostUseCase;
    private final DeletePostUseCase deletePostUseCase;

    @PostMapping
    @Operation(summary = "게시글 생성", description = "게시글을 생성합니다.")
    public PostId createPost(
            @Valid @RequestBody CreatePostRequest request,
            Long memberId
    ) {
        return createPostUseCase.createPost(request.toCommand(memberId));
    }

    @PatchMapping("/{postId}")
    @Operation(summary = "게시글 수정", description = "게시글의 제목, 내용, 이미지를 수정합니다.")
    public PostId updatePost(
            @PathVariable Long postId,
            @Valid @RequestBody UpdatePostRequest request,
            Long memberId
    ) {

        return updatePostUseCase.updatePost(request.toCommand(postId, memberId));
    }

    @DeleteMapping("/{postId}")
    @Operation(summary = "게시글 삭제", description = "게시글을 삭제합니다.")
    public void deletePost(
            @PathVariable Long postId,
            Long requesterId
    ) {
        DeletePostCommand command = new DeletePostCommand(postId,requesterId);
        deletePostUseCase.deletePost(command);
    }
}
