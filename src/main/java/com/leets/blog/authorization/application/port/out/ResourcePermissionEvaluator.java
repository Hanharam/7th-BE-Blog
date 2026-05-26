package com.leets.blog.authorization.application.port.out;

import com.leets.blog.authorization.domain.ResourcePermission;
import com.leets.blog.authorization.domain.ResourceType;
import com.leets.blog.authorization.domain.SubjectAttributes;

public interface ResourcePermissionEvaluator {

    ResourceType supportedResourceType();

    boolean evaluate(SubjectAttributes subject, ResourcePermission resourcePermission);
}
