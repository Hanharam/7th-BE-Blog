package com.leets.blog.repeat.exception;

import com.leets.blog.global.response.code.BaseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum RepeatErrorCode implements BaseCode {

    INVALID_REPEAT_STATUS(HttpStatus.BAD_REQUEST, "REPEAT-001", "입력 상태가 유효하지 않습니다."),
    ;
    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
