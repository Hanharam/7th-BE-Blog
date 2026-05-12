package com.leets.blog.global.security;

import static com.leets.blog.global.security.JwtAuthenticationFilter.JWT_ERROR_ATTRIBUTE;
import static com.leets.blog.global.security.JwtAuthenticationFilter.JWT_UNKNOWN_ERROR_ATTRIBUTE;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leets.blog.authentication.domain.exception.AuthenticationDomainException;
import com.leets.blog.global.exception.constant.CommonErrorCode;
import com.leets.blog.global.response.ApiResponse;
import com.leets.blog.global.response.code.BaseCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApiAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException {
        BaseCode errorCode = resolveErrorCode(request, authException);

        ApiResponse<Object> body = ApiResponse.onFailure(
                errorCode.getCode(),
                errorCode.getMessage(),
                null
        );

        response.setStatus(errorCode.getHttpStatus().value());
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(objectMapper.writeValueAsString(body));
    }

    private BaseCode resolveErrorCode(HttpServletRequest request, AuthenticationException authException) {
        Object jwtError = request.getAttribute(JWT_ERROR_ATTRIBUTE);
        if (jwtError instanceof AuthenticationDomainException domainException) {
            return domainException.getBaseCode();
        }

        Object unknownError = request.getAttribute(JWT_UNKNOWN_ERROR_ATTRIBUTE);
        if (unknownError != null) {
            return CommonErrorCode.INTERNAL_SERVER_ERROR;
        }

        Throwable cause = authException.getCause();
        if (cause instanceof AuthenticationDomainException domainException) {
            return domainException.getBaseCode();
        }

        return CommonErrorCode.UNAUTHORIZED;
    }
}
