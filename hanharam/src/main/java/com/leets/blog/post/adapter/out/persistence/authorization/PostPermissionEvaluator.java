package com.leets.blog.post.adapter.out.persistence.authorization;

import com.leets.blog.authorization.application.port.out.ResourcePermissionEvaluator;
import com.leets.blog.authorization.domain.ResourcePermission;
import com.leets.blog.authorization.domain.ResourceType;
import com.leets.blog.authorization.domain.SubjectAttributes;
import com.leets.blog.common.enums.Role;
import com.leets.blog.post.application.port.out.LoadPostPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class PostPermissionEvaluator implements ResourcePermissionEvaluator {

    private final LoadPostPort loadPostPort;


    @Override
    public ResourceType supportedResourceType() {
        return ResourceType.POST;
    }

    @Override
    public boolean evaluate(SubjectAttributes subject, ResourcePermission resourcePermission) {
        // 리소스 검증
        resourcePermission.resourceType().validatePermission(resourcePermission.permission());

        return switch (resourcePermission.permission()) {
            case READ, WRITE -> true; // 읽고 쓰기 모든 회원 허용

            case EDIT -> isAuthor(resourcePermission.getResourceIdAsLong(), subject.memberId());

            case DELETE -> isAuthorOrAdmin(resourcePermission.getResourceIdAsLong(), subject);

            default -> {
                log.warn("지원하지 않는 권한 타입: {}", resourcePermission.permission());
                yield false;
            }
        };
    }

    /**
     * 작성자 본인인지 확인
     */
    private boolean isAuthor(Long postId, Long memberId) {
        if (postId == null) return false;

        return loadPostPort.findById(postId)
                .map(post -> post.getMemberId().equals(memberId))
                .orElse(false);
    }

    /**
     * 작성자 본인 또는 관리자(ADMIN)인지 확인
     */
    private boolean isAuthorOrAdmin(Long postId, SubjectAttributes subject) {
        if (postId == null) return false;

        if (subject.role() == Role.ADMIN) return true;

        return isAuthor(postId, subject.memberId());
    }
}
