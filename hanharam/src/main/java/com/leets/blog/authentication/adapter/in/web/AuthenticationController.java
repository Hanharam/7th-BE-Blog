package com.leets.blog.authentication.adapter.in.web;

import com.leets.blog.authentication.adapter.in.web.dto.request.LoginRequest;
import com.leets.blog.authentication.adapter.in.web.dto.request.SignUpRequest;
import com.leets.blog.authentication.adapter.in.web.dto.response.AuthResponse;
import com.leets.blog.authentication.application.port.in.command.AuthenticateMemberUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@Tag(name = "Authentication | 인증", description = "회원가입 및 로그인 API")
public class AuthenticationController {

    private final AuthenticateMemberUseCase authenticateMemberUseCase;

    @PostMapping("/signup")
    @Operation(summary = "회원가입", description = "이메일과 비밀번호로 회원가입을 진행합니다.")
    public AuthResponse signUp(@Valid @RequestBody SignUpRequest request) {
        return AuthResponse.from(authenticateMemberUseCase.signUp(request.toCommand()));
    }

    @PostMapping("/login")
    @Operation(summary = "로그인", description = "이메일과 비밀번호로 로그인하고 JWT를 발급합니다.")
    public AuthResponse login(@Valid @RequestBody LoginRequest request) {
        return AuthResponse.from(authenticateMemberUseCase.login(request.toCommand()));
    }
}
