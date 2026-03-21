package com.leets.blog.global.exception;

import com.leets.blog.global.exception.constant.CommonErrorCode;
import com.leets.blog.global.response.ApiResponse;
import com.leets.blog.global.response.code.BaseCode;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice(annotations = {RestController.class})
@RequiredArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Value("${spring.profiles.active:local}")
    private String activeProfile;

    /**
     * 커스텀 예외 핸들러
     */
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> onTrowException(BusinessException e, WebRequest request) {
        log.warn("[BUSINESS EXCEPTION domain={}, code={}, message={}", e.getDomain(), e.getBaseCode().getCode(),
                e.getMessage(), e);

        return buildResponse(e, e.getBaseCode(), HttpHeaders.EMPTY, request, e.getMessage());
    }

    /**
     * 정의되지 않은 모든 예외의 기본 핸들러, 실제 운영 환경(prod)에서는 상세 에러 메시지 숨김
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUnhandledException(Exception e, WebRequest request) {
        log.error("[UNHANDLED EXCEPTION] {}", e.getMessage(), e);

        String errorDetail = isProduction()
                ? "서버 오류가 발생했습니다."
                : e.getMessage();

        return buildResponse(e, CommonErrorCode.INTERNAL_SERVER_ERROR, HttpHeaders.EMPTY, request, errorDetail);
    }

    /**
     * @Valid 검증 실패 예외 처리
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException e,
            HttpHeaders headers, HttpStatusCode status,
            WebRequest request
    ) {

        Map<String, String> errors = new LinkedHashMap<>();

        e.getBindingResult().getFieldErrors()
                .forEach(fieldError -> {
                    String fieldName = fieldError.getField();
                    String errorMessage = Optional.ofNullable(fieldError.getDefaultMessage()).orElse("");
                    errors.merge(fieldName, errorMessage,
                            (existingErrorMessage, newErrorMessage) -> existingErrorMessage + ", "
                                    + newErrorMessage);
                });

        log.warn("[VALIDATION ERROR] {}", errors);

        return buildResponse(e, CommonErrorCode.BAD_REQUEST, HttpHeaders.EMPTY, request, errors);
    }

    /**
     * Validation 제약 조건 위반 예외 처리
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolation(
            ConstraintViolationException e,
            WebRequest request
    ) {
        String messages = e.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(", "));

        log.warn("[CONSTRAINT VIOLATION] {}", messages);

        return buildResponse(e, CommonErrorCode.BAD_REQUEST, HttpHeaders.EMPTY, request, messages);
    }

    /**
     * 통합 에러 응답 빌더
     */
    private ResponseEntity<Object> buildResponse(
            Exception e, BaseCode code,
            HttpHeaders headers, WebRequest request, Object detail) {

        ApiResponse<Object> body = ApiResponse.onFailure(code.getCode(), code.getMessage(), detail);
        return super.handleExceptionInternal(e, body, headers, code.getHttpStatus(), request);
    }

    private ResponseEntity<Object> buildResponse(
            Exception e, BaseCode code,
            HttpHeaders headers, WebRequest request, Object detail,
            String message) {
        ApiResponse<Object> body = ApiResponse.onFailure(code.getCode(), message, detail);
        return super.handleExceptionInternal(e, body, headers, code.getHttpStatus(), request);
    }

    /**
     * 운영(prod) 환경 여부 확인
     */
    private boolean isProduction() {
        return "prod".equalsIgnoreCase(activeProfile);
    }
}
