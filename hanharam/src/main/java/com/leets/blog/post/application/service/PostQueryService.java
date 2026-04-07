package com.leets.blog.post.application.service;

import com.leets.blog.common.pagination.PageResponse;
import com.leets.blog.member.application.port.out.out.LoadMemberPort;
import com.leets.blog.post.application.port.in.query.GetPostDetailUseCase;
import com.leets.blog.post.application.port.in.query.GetPostListUseCase;
import com.leets.blog.post.application.port.in.query.dto.PostInfo;
import com.leets.blog.post.application.port.out.LoadPostPort;
import com.leets.blog.post.domain.Post;
import com.leets.blog.post.domain.exception.PostDomainException;
import com.leets.blog.post.domain.exception.PostErrorCode;
import lombok.RequiredArgsConstructor;
import com.leets.blog.common.pagination.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostQueryService implements GetPostListUseCase, GetPostDetailUseCase {

    private final LoadPostPort loadPostPort;
    private final LoadMemberPort loadMemberPort;

    @Override
    public PostInfo getPostDetail(Long postId) {
        // 게시글 조회, 존재 여부 검증
        Post post = loadPostPort.findById(postId)
                .orElseThrow(() -> new PostDomainException(PostErrorCode.POST_NOT_FOUND));

        // 작성자 닉네임 조회
        String memberNickName = loadMemberPort.findNicknameById(post.getMemberId());

        // DTO 반환 후 반환
        return PostInfo.of(post, memberNickName);
    }

    @Override
    public PageResponse<PostInfo> getPostList(PageRequest pageRequest) {
        PageResponse<Post> postPage = loadPostPort.findAll(pageRequest);
        return convertToPostInfoPage(postPage);
    }

    private PageResponse<PostInfo> convertToPostInfoPage(PageResponse<Post> postPage) {
        // 게시글이 없는 경우 빈 리스트 반환
        if (postPage.content().isEmpty()) {
            return new PageResponse<>(
                    Collections.emptyList(),
                    postPage.page(),
                    postPage.size(),
                    postPage.totalElements(),
                    postPage.totalPages(),
                    postPage.hasNext(),
                    postPage.hasPrevious()
            );
        }

        // 1. 게시글 작성자들의 ID 목록 추출
        Set<Long> memberIds = postPage.content().stream()
                .map(Post::getMemberId)
                .collect(Collectors.toSet());

        // 2. 닉네임 일괄 조회
        Map<Long, String> memberNicknameMap = loadMemberPort.findNicknamesByIds(memberIds);

        // 3. Post 도메인을 PostInfo DTO로 변환
        List<PostInfo> postInfos = postPage.content().stream()
                .map(post -> {
                    String nickname = memberNicknameMap.getOrDefault(post.getMemberId(), "알 수 없음");
                    return PostInfo.of(post, nickname);
                })
                .toList();

        // 4. 변환된 DTO 리스트를 새로운 PageResponse로 재조립하여 반환
        return new PageResponse<>(
                postInfos,
                postPage.page(),
                postPage.size(),
                postPage.totalElements(),
                postPage.totalPages(),
                postPage.hasNext(),
                postPage.hasPrevious()
        );
    }

}
