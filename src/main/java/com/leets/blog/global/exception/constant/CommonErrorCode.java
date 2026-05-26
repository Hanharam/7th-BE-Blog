package com.leets.blog.global.exception.constant;

import com.leets.blog.global.response.code.BaseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 프로젝트 전체에서 사용되는 COMMON 에러
 */
@Getter
@AllArgsConstructor
public enum CommonErrorCode implements BaseCode {

    // COMMON: 일반 상태 코드
    INTERNAL_SERVER_ERROR(
            HttpStatus.INTERNAL_SERVER_ERROR, "COMMON-500", "예기치 않은 서버 에러가 발생했습니다."),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "COMMON-400", "잘못된 요청입니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "COMMON-401", "인증이 필요합니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON-403", "허용되지 않는 요청입니다."),
    NOT_FOUND(HttpStatus.NOT_FOUND, "COMMON-404", "요청한 리소스를 찾을 수 없습니다."),
    NOT_IMPLEMENTED(HttpStatus.NOT_IMPLEMENTED, "COMMON-501", "아직 구현되지 않은 기능입니다."),

    // ENVIRONMENT: SpringBoot 실행환경 관련 에러 -> local, dev 전용 test api 운영 환경에서 사용할 때 예외
    INVALID_ENV(HttpStatus.BAD_REQUEST, "ENV-001", "현재 실행 환경에서는 사용할 수 없는 기능입니다.")
    ;
    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
