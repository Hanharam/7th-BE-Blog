package com.leets.blog.authentication.domain.exception;

import com.leets.blog.global.response.code.BaseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum AuthenticationErrorCode implements BaseCode {

    DUPLICATE_EMAIL(HttpStatus.CONFLICT, "AUTH-001", "이미 사용 중인 이메일입니다."),
    INVALID_EMAIL_FORMAT(HttpStatus.BAD_REQUEST, "AUTH-002", "올바른 이메일 형식이 아닙니다."),
    INVALID_PASSWORD_FORMAT(HttpStatus.BAD_REQUEST, "AUTH-003", "비밀번호는 8자 이상 64자 이하이며 영문과 숫자를 포함해야 합니다."),
    INVALID_CREDENTIALS(HttpStatus.UNAUTHORIZED, "AUTH-004", "이메일 또는 비밀번호가 올바르지 않습니다."),
    WRONG_JWT_SIGNATURE(HttpStatus.UNAUTHORIZED, "AUTH-005", "잘못된 JWT 서명입니다."),
    EXPIRED_JWT_TOKEN(HttpStatus.UNAUTHORIZED, "AUTH-006", "만료된 JWT 토큰입니다."),
    UNSUPPORTED_JWT(HttpStatus.UNAUTHORIZED, "AUTH-007", "지원되지 않는 JWT 토큰입니다."),
    INVALID_JWT(HttpStatus.UNAUTHORIZED, "AUTH-008", "유효하지 않은 JWT 토큰입니다."),
    INVALID_AUTH_HEADER(HttpStatus.UNAUTHORIZED, "AUTH-009", "Authorization 헤더 형식이 올바르지 않습니다."),
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "AUTH-010", "회원을 찾을 수 없습니다."),
    INVALID_PROFILE_INPUT(HttpStatus.BAD_REQUEST, "AUTH-011", "이름과 닉네임은 필수입니다."),
    ACCOUNT_LINK_REQUIRED(HttpStatus.CONFLICT, "AUTH-012", "동일한 이메일의 계정이 존재합니다. 계정 연동 페이지에서 연동해주세요."),
    OAUTH_TOKEN_VERIFICATION_FAILED(HttpStatus.UNAUTHORIZED, "AUTH-013", "OAuth 인증 처리에 실패했습니다."),
    OAUTH_CONFIGURATION_MISSING(HttpStatus.INTERNAL_SERVER_ERROR, "AUTH-014", "OAuth 설정이 누락되었습니다."),
    REFRESH_TOKEN_NOT_FOUND(HttpStatus.UNAUTHORIZED, "AUTH-015", "저장된 리프레시 토큰을 찾을 수 없습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
