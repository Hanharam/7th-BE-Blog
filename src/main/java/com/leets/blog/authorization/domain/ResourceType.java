package com.leets.blog.authorization.domain;

import lombok.Getter;

import java.util.Set;

/**
 * 권한 체크 대상이 되는 리소스 타입
 */
@Getter
public enum ResourceType {

    POST("post", "게시글", Set.of(
            PermissionType.READ, PermissionType.WRITE, PermissionType.EDIT, PermissionType.DELETE
    )),
    COMMENT("comment", "댓글", Set.of(
            PermissionType.READ, PermissionType.WRITE, PermissionType.EDIT, PermissionType.DELETE
    )),
    MEMBER("member", "회원",
            Set.of(PermissionType.READ, PermissionType.EDIT, PermissionType.DELETE));

    private final String code;
    private final String description;
    private final Set<PermissionType> supportedPermissions;

    ResourceType(String code, String description, Set<PermissionType> supportedPermissions) {
        this.code = code;
        this.description = description;
        this.supportedPermissions = Set.copyOf(supportedPermissions);
    }

    public boolean supports(PermissionType permission) {
        return supportedPermissions.contains(permission);
    }

    public void validatePermission(PermissionType permission) {
        if (!supports(permission)) {
            throw new IllegalArgumentException(
                    String.format("리소스 '%s'은(는) '%s' 권한을 지원하지 않습니다.", this.name(), permission)
            );
        }
    }
}
