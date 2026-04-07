package com.leets.blog.post.adapter.out.persistence.entity;

import com.leets.blog.common.BaseEntity;
import com.leets.blog.post.domain.Post;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "post")
public class PostJpaEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "member_id", nullable = false)
    private Long memberId;

    @Builder
    private PostJpaEntity(String title, String content, String imageUrl, Long memberId) {
        this.title = title;
        this.content = content;
        this.imageUrl = imageUrl;
        this.memberId = memberId;
    }

    public static PostJpaEntity from(Post post) {
        return PostJpaEntity.builder()
                .title(post.getTitle())
                .content(post.getContent())
                .imageUrl(post.getImageUrl())
                .memberId(post.getMemberId())
                .build();
    }

    public Post toDomain() {
        return Post.reconstruct(
                new Post.PostId(this.id),
                this.title,
                this.content,
                this.imageUrl,
                this.memberId,
                this.getCreatedAt(),
                this.getUpdatedAt()
        );
    }

    public void update(Post post) {
        this.title = post.getTitle();
        this.content = post.getContent();
        this.imageUrl = post.getImageUrl();
    }
}