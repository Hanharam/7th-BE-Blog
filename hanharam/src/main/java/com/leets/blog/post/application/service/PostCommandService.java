package com.leets.blog.post.application.service;

import com.leets.blog.post.application.port.in.commad.CreatePostUseCase;
import com.leets.blog.post.application.port.in.commad.UpdatePostUseCase;
import com.leets.blog.post.application.port.in.commad.dto.CreatePostCommand;
import com.leets.blog.post.application.port.in.commad.dto.UpdatePostCommand;
import com.leets.blog.post.application.port.out.LoadPostPort;
import com.leets.blog.post.application.port.out.SavePostPort;
import com.leets.blog.post.domain.Post;
import com.leets.blog.post.domain.Post.PostId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PostCommandService implements CreatePostUseCase, UpdatePostUseCase {

    private final SavePostPort savePostPort;
    private final LoadPostPort loadPostPort;

    // Post 생성
    @Override
    public PostId createPost(CreatePostCommand command) {

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

        // 도메인 조회
        Post post = loadPostPort.loadPost(new Post.PostId(command.postId()));

        post.update(
                command.title(),
                command.content(),
                command.imageUrl(),
                command.requesterId()
        );

        Post savedPost = savePostPort.save(post);

        return savedPost.getPostId();
    }

}
