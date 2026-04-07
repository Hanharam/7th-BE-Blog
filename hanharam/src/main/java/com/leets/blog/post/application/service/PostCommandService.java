package com.leets.blog.post.application.service;

import com.leets.blog.member.application.port.out.out.LoadMemberPort;
import com.leets.blog.post.application.port.in.commad.CreatePostUseCase;
import com.leets.blog.post.application.port.in.commad.DeletePostUseCase;
import com.leets.blog.post.application.port.in.commad.UpdatePostUseCase;
import com.leets.blog.post.application.port.in.commad.dto.CreatePostCommand;
import com.leets.blog.post.application.port.in.commad.dto.DeletePostCommand;
import com.leets.blog.post.application.port.in.commad.dto.UpdatePostCommand;
import com.leets.blog.post.application.port.out.LoadPostPort;
import com.leets.blog.post.application.port.out.SavePostPort;
import com.leets.blog.post.domain.Post;
import com.leets.blog.post.domain.Post.PostId;
import com.leets.blog.post.domain.exception.PostDomainException;
import com.leets.blog.post.domain.exception.PostErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PostCommandService implements CreatePostUseCase, UpdatePostUseCase, DeletePostUseCase {

    private final SavePostPort savePostPort;
    private final LoadPostPort loadPostPort;
    private final LoadMemberPort loadMemberPort;

    // Post 생성
    @Override
    public PostId createPost(CreatePostCommand command) {

        // [임시 검증] 멤버가 존재하는지 확인
        validateMemberExists(command.memberId());

        // 도메인 생성
        Post newPost = Post.createPost(
                command.title(),
                command.content(),
                command.memberId()
        );

        // out 포트를 통해 DB에 저장하는 것을 위임, 영속성 어댑터가 DB 저장 후 반환
        Post savedPost = savePostPort.save(newPost);

        return savedPost.getPostId();
    }


    // Post 수정
    @Override
    public PostId updatePost(UpdatePostCommand command) {

        // [임시 검증] 멤버가 존재하는지 확인, 추후에 인가 시스템 적용 ABAC
        validateMemberExists(command.requesterId());

        // 도메인 조회
        Post post = loadPostPort.findPost(new Post.PostId(command.postId()));

        post.update(
                command.title(),
                command.content(),
                command.imageUrl(),
                command.requesterId()
        );

        Post savedPost = savePostPort.save(post);

        return savedPost.getPostId();
    }

    @Override
    public void deletePost(DeletePostCommand command) {

        // [임시 검증] 멤버가 존재하는지 확인
        validateMemberExists(command.requesterId());

        // 게시글 조회
        Post post = loadPostPort.findById(command.postId())
                .orElseThrow(() -> new PostDomainException(PostErrorCode.POST_NOT_FOUND));

        // 권한 검증
        post.validateDeletionPermission(command.requesterId());

        // 삭제
        savePostPort.deleteById(command.postId());
    }

    private void validateMemberExists(Long memberId) {
        if (!loadMemberPort.existsById(memberId)) {
            throw new PostDomainException(PostErrorCode.MEMBER_NOT_FOUND);
        }
    }
}
