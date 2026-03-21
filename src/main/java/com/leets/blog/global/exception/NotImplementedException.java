package com.leets.blog.global.exception;

import com.leets.blog.global.exception.constant.CommonErrorCode;
import com.leets.blog.global.exception.constant.Domain;
import lombok.Getter;

/**
 * 컨트롤러에서 설계만 되어있고, 아직 구현되지 않은 경우 해당 에러를 발생 시킴
 */
@Getter
public class NotImplementedException extends BusinessException {

    public NotImplementedException(String message) {
        super(Domain.COMMON, CommonErrorCode.NOT_IMPLEMENTED, message);
    }

    public NotImplementedException() {
        super(Domain.COMMON, CommonErrorCode.NOT_IMPLEMENTED);
    }
}
