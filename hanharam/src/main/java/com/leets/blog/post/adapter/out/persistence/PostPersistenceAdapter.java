package com.leets.blog.post.adapter.out.persistence;

import com.leets.blog.common.pagination.PageRequest;
import com.leets.blog.common.pagination.PageResponse;
import com.leets.blog.post.adapter.out.persistence.entity.PostJpaEntity;
import com.leets.blog.post.application.port.out.LoadPostPort;
import com.leets.blog.post.application.port.out.SavePostPort;
import com.leets.blog.post.domain.Post;
import com.leets.blog.post.domain.Post.PostId;
import com.leets.blog.post.domain.exception.PostDomainException;
import com.leets.blog.post.domain.exception.PostErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

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
    public void deleteById(Long postId) {
        postRepository.deleteById(postId);
    }

    @Override
    public Post findPost(PostId postId) {
        PostJpaEntity entity = postRepository.findById(postId.id())
                .orElseThrow(() -> new PostDomainException(PostErrorCode.POST_NOT_FOUND));

        return entity.toDomain();
    }

    @Override
    public Optional<Post> findById(Long postId) {
        return postRepository.findById(postId)
                .map(PostJpaEntity::toDomain);
    }

    @Override
    public PageResponse<Post> findAll(PageRequest pageRequest) {
        // 1. 커스텀 PageRequest -> Spring Data Pageable 변환
        Pageable jpaPageable = org.springframework.data.domain.PageRequest.of(
                pageRequest.page() - 1,
                pageRequest.size(),
                Sort.by(Sort.Direction.DESC, "createdAt") // 최신순 정렬
        );

        // 2. Spring Data JPA로 DB 전체 조회
        Page<PostJpaEntity> entityPage = postRepository.findAll(jpaPageable);

        // 3. Spring의 Page<Entity> -> 커스텀 PageResponse<Domain> 변환
        List<Post> posts = entityPage.getContent().stream()
                .map(PostJpaEntity::toDomain)
                .toList();

        return new PageResponse<>(
                posts,
                pageRequest.page(),              // 1-based 유지
                entityPage.getSize(),
                entityPage.getTotalElements(),
                entityPage.getTotalPages(),
                entityPage.hasNext(),
                entityPage.hasPrevious()
        );
    }
}
