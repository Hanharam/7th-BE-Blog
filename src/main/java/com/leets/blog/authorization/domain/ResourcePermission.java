package com.leets.blog.authorization.domain;

import java.util.Objects;

/**
 * 특정 리소스에 대해 검사할 권한 요청 정보
 */
public record ResourcePermission(
        ResourceType resourceType,
        String resourceId,
        PermissionType permission
) {
    public ResourcePermission {
        Objects.requireNonNull(resourceType, "리소스 타입은 필수입니다.");
        Objects.requireNonNull(permission, "권한 타입은 필수입니다.");
    }

    public Long getResourceIdAsLong() {
        if (resourceId == null || resourceId.isBlank()) {
            return null;
        }

        try {
            return Long.parseLong(resourceId);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
