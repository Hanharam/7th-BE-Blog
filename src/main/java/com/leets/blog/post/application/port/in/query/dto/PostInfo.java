package com.leets.blog.post.application.port.in.query.dto;

import com.leets.blog.post.domain.Post;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record PostInfo(
        Long postId,
        String title,
        String content,
        String imageUrl,
        String memberNickname,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static PostInfo of(Post post, String memberNickname) {
        return PostInfo.builder()
                .postId(post.getPostId().id())
                .title(post.getTitle())
                .content(post.getContent())
                .imageUrl(post.getImageUrl())
                .memberNickname(memberNickname)
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .build();
    }
}
