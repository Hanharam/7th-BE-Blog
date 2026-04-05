package com.leets.blog.post.adapter.out.persistence;

import com.leets.blog.post.adapter.out.persistence.entity.PostJpaEntity;
import com.leets.blog.post.application.port.out.LoadPostPort;
import com.leets.blog.post.application.port.out.SavePostPort;
import com.leets.blog.post.domain.Post;
import com.leets.blog.post.domain.Post.PostId;
import com.leets.blog.post.domain.exception.PostDomainException;
import com.leets.blog.post.domain.exception.PostErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostPersistenceAdapter implements SavePostPort, LoadPostPort {

    private final PostRepository postRepository;

    @Override
    public Post save(Post post) {
        PostJpaEntity entity;

        if (post.getPostId() == null) {
            // CREATE: 새 엔티티 생성
            entity = PostJpaEntity.from(post);
        } else {
            // UPDATE: 기존 엔티티 조회 후 수정
            entity = postRepository.findById(post.getPostId().id())
                    .orElseThrow(() -> new PostDomainException(PostErrorCode.POST_NOT_FOUND));
            entity.update(post);
        }

        PostJpaEntity saved = postRepository.save(entity);
        return saved.toDomain();
    }

    @Override
    public Post loadPost(PostId postId) {
        PostJpaEntity entity = postRepository.findById(postId.id())
                .orElseThrow(() -> new PostDomainException(PostErrorCode.POST_NOT_FOUND));

        return entity.toDomain();
    }
}
