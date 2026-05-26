package com.leets.blog.comment.domain;

import com.leets.blog.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "comment")
public class CommentJpaEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "post_id", nullable = false)
    private Long postId;

    @Column(name = "member_id", nullable = false)
    private Long memberId;

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(name = "parent_id")
    private Long parentId;

    @Builder
    private CommentJpaEntity(Long postId, Long memberId, String content, Long parentId) {
        this.postId = postId;
        this.memberId = memberId;
        this.content = content;
        this.parentId = parentId;
    }

    // todo: toDomain 임시 설정 -> Comment 도메인 생성 후 메서드 작성하기
    public Comment toDomain() {
        return null;
    }
}
