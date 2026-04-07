package com.leets.blog.post.domain.exception;

import com.leets.blog.global.response.code.BaseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum PostErrorCode implements BaseCode {

    POST_NOT_FOUND(HttpStatus.NOT_FOUND, "POST-001", "게시글을 찾을 수 없습니다."),

    INVALID_POST_TITLE(HttpStatus.BAD_REQUEST, "POST-002", "게시글 제목이 유효하지 않습니다."),
    INVALID_POST_CONTENT(HttpStatus.BAD_REQUEST, "POST-003", "게시글 내용이 유효하지 않습니다."),
    INVALID_POST_AUTHOR(HttpStatus.BAD_REQUEST, "POST-004", "작성자 ID는 필수입니다."),
    INVALID_ID(HttpStatus.BAD_REQUEST, "POST-005", "ID는 양수입니다."),
    INVALID_TIMESTAMP(HttpStatus.BAD_REQUEST,"POST-006","생성/수정일자가 유효하지 않습니다." ),
    INVALID_TIMESTAMP_ORDER(HttpStatus.BAD_REQUEST, "POST-007", "수정일자가 생성일자보다 빠를 수 없습니다."),

    POST_NOT_OWNED(HttpStatus.FORBIDDEN, "POST-008", "본인의 게시글만 수정/삭제할 수 있습니다."),
    MEMBER_NOT_FOUND(HttpStatus.BAD_REQUEST, "POST-011", "존재하지 않는 사용자 입니다.");
    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
