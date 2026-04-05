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
    POST_SAVE_REQUIRES_AUTHOR(HttpStatus.BAD_REQUEST, "POST-009", "새 게시글 생성 시에는 작성자 정보가 필요합니다."),
    POST_UPDATE_INVALID_CALL(HttpStatus.BAD_REQUEST, "POST-010", "이미 ID가 있는 게시글은 update용 save를 사용하세요."),
    UNAUTHORIZED_POST_UPDATE(HttpStatus.BAD_REQUEST, "POST-011", "게시글 작성자 이외에는 수정 권한이 없습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
