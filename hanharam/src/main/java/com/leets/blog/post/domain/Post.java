package com.leets.blog.post.domain;

import com.leets.blog.post.domain.exception.PostDomainException;
import com.leets.blog.post.domain.exception.PostErrorCode;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
public class Post {
    @Getter
    private final PostId postId;

    @Getter
    private String title;

    @Getter
    private String content;

    @Getter
    private String imageUrl;

    @Getter
    private final Long memberId;

    @Getter
    private final LocalDateTime createdAt;

    @Getter
    private LocalDateTime updatedAt;


    public static Post createPost(String title, String content, Long memberId) {
        validateCommonFields(title, content);
        validateMemberId(memberId);

        LocalDateTime now = LocalDateTime.now();

        return Post.builder()
                .title(title)
                .content(content)
                .memberId(memberId)
                .createdAt(now)
                .updatedAt(now)
                .build();
    }

    // 기존 게시글 재구성
    public static Post reconstruct(
            PostId postId,
            String title,
            String content,
            String imageUrl,
            Long memberId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        validateCommonFields(title, content);
        validateMemberId(memberId);
        validateTimestamps(createdAt, updatedAt);

        return Post.builder()
                .postId(postId)
                .title(title)
                .content(content)
                .imageUrl(imageUrl)
                .memberId(memberId)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .build();
    }

    // 게시글 수정
    public void update(String title, String content, String imageUrl, Long requesterId) {
        validateAuthorization(requesterId);
        validateCommonFields(title, content);

        this.title = title;
        this.content = content;
        this.imageUrl = imageUrl;
        this.updatedAt = LocalDateTime.now();
    }

    // 수정 권한 검증
    private void validateAuthorization(Long requesterId) {
        if (!this.memberId.equals(requesterId)) {
            throw new PostDomainException(PostErrorCode.POST_NOT_OWNED);
        }
    }


    // title, content 검증
    private static void validateCommonFields(String title, String content) {
        if (title == null || title.isBlank()) {
            throw new PostDomainException(PostErrorCode.INVALID_POST_TITLE);
        }
        if (content == null || content.isBlank()) {
            throw new PostDomainException(PostErrorCode.INVALID_POST_CONTENT);
        }
    }

    // 멤버 Id 검증
    private static void validateMemberId(Long memberId) {
        if (memberId == null) {
            throw new PostDomainException(PostErrorCode.INVALID_POST_AUTHOR);
        }
    }

    // 생성/수정일자 검증
    public static void validateTimestamps(LocalDateTime createdAt, LocalDateTime updatedAt) {
        if (createdAt == null || updatedAt == null) {
            throw new PostDomainException(PostErrorCode.INVALID_TIMESTAMP);
        }

        if (createdAt.isAfter(updatedAt)) {
            throw new PostDomainException(PostErrorCode.INVALID_TIMESTAMP_ORDER);
        }
    }

    public void validateDeletionPermission(Long requesterId) {
        if (!this.memberId.equals(requesterId)) {
            throw new PostDomainException(PostErrorCode.POST_NOT_OWNED);
        }
    }

    // PostId 검증 -> 비즈니스 로직 많아지면 클래스로 빼기
    @Builder
    public record PostId(Long id) {
        public PostId{
            if (id <= 0) {
                throw new PostDomainException(PostErrorCode.INVALID_ID);
            }
        }
    }
}
