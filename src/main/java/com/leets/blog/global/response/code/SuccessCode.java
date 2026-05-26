package com.leets.blog.global.response.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SuccessCode implements BaseCode {

    _OK(HttpStatus.OK, "COMMON200", "성공했습니다.")
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
